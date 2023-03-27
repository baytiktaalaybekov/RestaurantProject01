package peaksoft.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.entity.MenuItem;
import peaksoft.entity.SubCategory;

import java.util.List;
@Getter @Setter
@NoArgsConstructor
public class MenuItemPaginationResponse {
    private List<MenuItem> menuItems;
    private int currentPage;
    private int pageSize;
}
