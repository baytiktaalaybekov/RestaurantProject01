package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.SubCategoryRequest;
import peaksoft.dto.response.CategoryPaginationResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.SubCategoryPaginationResponse;
import peaksoft.dto.response.SubCategoryResponse;
import peaksoft.service.SubcategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/subcategories")
@RequiredArgsConstructor
public class SubCategoryAPI {

    private final SubcategoryService subCategoryService;


    @PostMapping("/{categoryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveSubCategory(@PathVariable Long categoryId,
                                          @RequestBody SubCategoryRequest subCategoryRequest) {
        return subCategoryService.save(categoryId, subCategoryRequest);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<SubCategoryResponse> getAllBySubCategory() {
        return subCategoryService.getAllBySubCategory();
    }

    @GetMapping("/getById/{subId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SubCategoryResponse getById(@PathVariable Long subId) {
        return subCategoryService.getSubCategoryById(subId);
    }

    @PutMapping("/{subId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse updateSub(@PathVariable Long subId,
                                    @RequestBody SubCategoryRequest subCategoryRequest) {
        return subCategoryService.update(subId, subCategoryRequest);
    }

    @DeleteMapping("/{categoryId}/{subCategoryId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteSub(@PathVariable Long categoryId,
                                    @PathVariable Long subCategoryId) {
        return subCategoryService.delete(categoryId, subCategoryId);
    }

    @GetMapping("/pagination")
    public SubCategoryPaginationResponse getSubCategoryResponse(@RequestParam int page,
                                                                @RequestParam int size) {
        return subCategoryService.getSubCategoryResponse(page, size);
    }
}
