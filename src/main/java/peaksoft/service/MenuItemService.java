package peaksoft.service;

import peaksoft.dto.request.MenuItemRequest;
import peaksoft.dto.response.MenuItemPaginationResponse;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.dto.response.MenuItemResponseSearch;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.MenuItem;

import java.util.List;

public interface MenuItemService {
    SimpleResponse saveManu(MenuItemRequest menuRequest);

    List<MenuItem> getAllMenus();

    MenuItemResponse getByMenuId(Long menuId);

    SimpleResponse updateMenu(Long menuId, MenuItemRequest request);

    SimpleResponse deleteManu(Long Id);

    List<MenuItemResponse> sortByPriceAndFilterVeganAndSearch(String word,Boolean vegan,String sort);

    List<MenuItemResponseSearch>globalSearch(String keyWord);


    MenuItemPaginationResponse getMenuItemResponse(int page, int size);
}
