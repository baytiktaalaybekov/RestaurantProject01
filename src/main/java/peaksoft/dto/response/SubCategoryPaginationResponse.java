package peaksoft.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.entity.Category;
import peaksoft.entity.SubCategory;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class SubCategoryPaginationResponse {
    private List<SubCategory> subCategories;
    private int currentPage;
    private int pageSize;


}
