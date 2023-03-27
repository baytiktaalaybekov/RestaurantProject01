package peaksoft.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.entity.Category;

import java.util.List;
@Getter @Setter
@NoArgsConstructor
public class CategoryPaginationResponse {
    private List<Category> categories;
    private int currentPage;
    private int pageSize;
}
