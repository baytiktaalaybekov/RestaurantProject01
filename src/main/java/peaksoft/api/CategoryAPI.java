package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CategoryRequest;
import peaksoft.dto.response.CategoryPaginationResponse;
import peaksoft.dto.response.CategoryResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.CategoryService;
import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryAPI {

    private final CategoryService categoryService;


    @PostMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse save(@RequestBody  CategoryRequest request) {
        return categoryService.saveCategory(request);
    }
    @GetMapping
    @PreAuthorize("permitAll()")
    public List<CategoryResponse>getAll(){
        return categoryService.getAll();
    }
    @DeleteMapping("/{id}")
    @PreAuthorize("permitAll()")
    public SimpleResponse delete(@PathVariable Long id){
        return categoryService.deleteById(id);
    }
    @GetMapping("/{id}")
    @PreAuthorize("permitAll()")
    public CategoryResponse getById(@PathVariable Long id){
        return categoryService.getById(id);
    }
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SimpleResponse update(@PathVariable Long id,@RequestBody CategoryRequest request){
        return categoryService.update(id,request);
    }

    @GetMapping("/pagination")
    public CategoryPaginationResponse getCategoryPagination(@RequestParam int page,
                                                            @RequestParam int size){
        return categoryService.getCategoryPagination(page,size);
    }

}
