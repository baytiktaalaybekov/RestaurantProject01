package peaksoft.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import peaksoft.dto.request.CategoryRequest;
import peaksoft.dto.response.CategoryPaginationResponse;
import peaksoft.dto.response.CategoryResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Category;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.CategoryRepository;
import peaksoft.service.CategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CategorySerImpl implements CategoryService {


    private final CategoryRepository categoryRepository;

    @Override
    public SimpleResponse saveCategory(CategoryRequest request) {
        Category category = new Category();
        category.setName(request.name());
        categoryRepository.save(category);
        return SimpleResponse.builder().status(HttpStatus.OK)
                .message("Successfully saved..").build();
    }
    @Override
    public List<CategoryResponse> getAll() {
        return categoryRepository.getAllCategory();
    }

    @Override
    public CategoryResponse getById(Long id) {
        return categoryRepository.getByIdCategory(id);
    }

    @Override
    public SimpleResponse deleteById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Not Found!"));
        categoryRepository.delete(category);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Successfully delete.").build();
    }

    @Override
    public SimpleResponse update(Long id, CategoryRequest request) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Not Found!"));
        category.setName(request.name());
        categoryRepository.save(category);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Update").build();
    }

    @Override
    public CategoryPaginationResponse getCategoryPagination(int page, int size) {
        PageRequest pageable= PageRequest.of(page-1,size, Sort.by("priceAverage"));
        Page<Category> pageCate = categoryRepository.findAll(pageable);
        CategoryPaginationResponse categoryPaginationResponse = new CategoryPaginationResponse();
        categoryPaginationResponse.setCategories(pageCate.getContent());
        categoryPaginationResponse.setCurrentPage(pageable.getPageNumber()+1);
        categoryPaginationResponse.setPageSize(pageCate.getTotalPages());
        return categoryPaginationResponse;
//
    }


}
