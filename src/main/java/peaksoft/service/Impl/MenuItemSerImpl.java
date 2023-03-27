package peaksoft.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.MenuItemRequest;
import peaksoft.dto.response.*;
import peaksoft.entity.*;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.StopListRepository;
import peaksoft.repository.SubcategoryRepository;
import peaksoft.service.MenuItemService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuItemSerImpl implements MenuItemService {

    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;
    private final StopListRepository stopListRepository;
    private final SubcategoryRepository subcategoryRepository;

    @Override
    public SimpleResponse saveManu( MenuItemRequest menuRequest) {
        if (!restaurantRepository.existsById(menuRequest.restId())){
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(String.format("Restaurant with id: %d doesn't exist", menuRequest.restId())).build();
        }

        SubCategory subCategory = subcategoryRepository.findById(menuRequest.subCategoryId()).orElseThrow();
        Restaurant restaurant = restaurantRepository.findById(menuRequest.restId()).orElseThrow();
        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuRequest.name());
        menuItem.setImage(menuRequest.image());
        menuItem.setDescription(menuRequest.description());
        if (menuRequest.price() < 0){
            return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST)
                    .message("Price must be greater than 0").build();
        }
        menuItem.setPrice(menuRequest.price());
        menuItem.setIsVegetarian(menuRequest.isVegetarian());
        menuItem.setSubcategory(subCategory);

        restaurant.addMenuItem(menuItem);
        subCategory.setMenuItem(menuItem);
        menuItem.setRestaurant(restaurant);
        menuItemRepository.save(menuItem);
        return SimpleResponse.builder().status(HttpStatus.OK).message(
                String.format("Manu with name: %s successfully SAVED", menuRequest.name())).build();
    }

    @Override
    public List<MenuItem> getAllMenus() {
        List<MenuItem> menus = menuItemRepository.findAll();
        List<StopList> date = stopListRepository.findByDate(LocalDate.now());
        for (StopList stopListResponse : date) {
            menus.remove(stopListResponse.getMenuItem());

        }
        return menus;

    }

    @Override
    public MenuItemResponse getByMenuId(Long menuId) {
        return menuItemRepository.getByMenuId(menuId).orElseThrow(() -> new NegativeArraySizeException(
                String.format("Menu with id: %d doesn't exist", menuId)
        ));
    }

    @Override
    public SimpleResponse updateMenu(Long menuId, MenuItemRequest request) {
        if (!menuItemRepository.existsById(menuId)){
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Menu with id:" + menuId + " doesn't exist").build();
        }
        MenuItem menuItem = menuItemRepository.findById(menuId).orElseThrow();
        menuItem.setName(request.name());
        menuItem.setImage(request.image());
        if (request.price() < 0){
            return SimpleResponse.builder().status(HttpStatus.BAD_REQUEST).message("Price must be greater than 0").build();
        }
        menuItem.setPrice(request.price());
        menuItem.setDescription(request.description());
        menuItem.setIsVegetarian(request.isVegetarian());

        menuItemRepository.save(menuItem);
        return SimpleResponse.builder().status(HttpStatus.OK).message(
                String.format("Menu with name: %s successfully UPDATED", request.name())).build();
    }

    @Override
    public SimpleResponse deleteManu(Long Id) {
        menuItemRepository.deleteById(Id);
        return SimpleResponse.builder().status(HttpStatus.OK)
                .message("Successfully deleted.!!").build();

    }

    @Override
    public List<MenuItemResponse> sortByPriceAndFilterVeganAndSearch(String word, Boolean vegan, String sort) {
        if (word.equalsIgnoreCase("asc")){
            return menuItemRepository.sortByAsc();
        }else if (sort.equalsIgnoreCase("desc")){
            return menuItemRepository.sortByDesc();
        }else {
            return menuItemRepository.getAllMenus();

        }
    }

    @Override
    public List<MenuItemResponseSearch> globalSearch(String keyWord) {
        return globalSearch(keyWord);
    }

    @Override
    public MenuItemPaginationResponse getMenuItemResponse(int page, int size) {
        PageRequest pageable= PageRequest.of(page-1,size, Sort.by("name"));
        Page<MenuItem> pageMenu = menuItemRepository.findAll(pageable);
        MenuItemPaginationResponse menuItemPaginationResponse= new MenuItemPaginationResponse();
        menuItemPaginationResponse.setMenuItems(pageMenu.getContent());
        menuItemPaginationResponse.setCurrentPage(pageable.getPageNumber()+1);
        menuItemPaginationResponse.setPageSize(pageMenu.getTotalPages());
        return menuItemPaginationResponse;
    }
}
