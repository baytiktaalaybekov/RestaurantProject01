package peaksoft.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.MenuItemRequest;
import peaksoft.dto.response.CategoryPaginationResponse;
import peaksoft.dto.response.MenuItemPaginationResponse;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.MenuItem;
import peaksoft.repository.MenuItemRepository;
import peaksoft.service.MenuItemService;

import java.util.List;

@RestController
@RequestMapping("/api/menuItem")
public class MenuItemAPI {

    private final MenuItemService menuItemService;
    private final MenuItemRepository menuItemRepository;

    public MenuItemAPI(MenuItemService menuItemService,
                       MenuItemRepository menuItemRepository) {
        this.menuItemService = menuItemService;
        this.menuItemRepository = menuItemRepository;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping()
    public SimpleResponse saveManu(@RequestBody MenuItemRequest menuItemRequest) {
        return menuItemService.saveManu(menuItemRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getAll")
    public List<MenuItemResponse> getAllMenuItem(@RequestParam(required = false) String word, @RequestParam
            (required = false, defaultValue = "asc") String sort, @RequestParam(required = false) Boolean isVegan) {
        return menuItemService.sortByPriceAndFilterVeganAndSearch(word, isVegan, sort);
    }

    @GetMapping
    public List<MenuItem> getAllMenuAndItem() {
        return menuItemService.getAllMenus();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{menuId}")
    public MenuItemResponse getByManuId(@PathVariable Long menuId) {
        return menuItemService.getByMenuId(menuId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{menuId}")
    public SimpleResponse updateMenu(@PathVariable Long menuId,
                                     @RequestBody MenuItemRequest request) {
        return menuItemService.updateMenu(menuId, request);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{menuId}")
    public SimpleResponse deleteMenu(@PathVariable Long menuId) {
        return menuItemService.deleteManu(menuId);

    }

    @GetMapping("/pagination")
    public MenuItemPaginationResponse getMenuItemResponse(@RequestParam int page,
                                                          @RequestParam int size) {
        return menuItemService.getMenuItemResponse(page, size);

    }
}
