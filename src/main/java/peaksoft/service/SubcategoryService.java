package peaksoft.service;

import peaksoft.dto.request.SubCategoryRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.SubCategoryPaginationResponse;
import peaksoft.dto.response.SubCategoryResponse;

import java.util.List;

public interface SubcategoryService {
    SimpleResponse save(Long categoryId, SubCategoryRequest subCategoryRequest);

    List<SubCategoryResponse> getAllBySubCategory();

    SubCategoryResponse getSubCategoryById(Long subId);

    SimpleResponse update(Long subId, SubCategoryRequest request);

    SimpleResponse delete(Long categoryId, Long subCategoryId);

    SubCategoryPaginationResponse getSubCategoryResponse(int page, int size);
}
