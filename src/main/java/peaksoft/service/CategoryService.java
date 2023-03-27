package peaksoft.service;

import peaksoft.dto.request.CategoryRequest;
import peaksoft.dto.response.CategoryPaginationResponse;
import peaksoft.dto.response.CategoryResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;

public interface CategoryService {
    SimpleResponse saveCategory(CategoryRequest request);
    List<CategoryResponse>getAll();
    CategoryResponse getById(Long id);
    SimpleResponse deleteById(Long id);
    SimpleResponse update(Long id,CategoryRequest request);

    CategoryPaginationResponse getCategoryPagination(int page, int size);
}
