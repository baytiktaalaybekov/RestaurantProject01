package peaksoft.service.Impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.SubCategoryRequest;
import peaksoft.dto.response.CategoryPaginationResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.SubCategoryPaginationResponse;
import peaksoft.dto.response.SubCategoryResponse;
import peaksoft.entity.Category;
import peaksoft.entity.SubCategory;
import peaksoft.exception.*;
import peaksoft.repository.CategoryRepository;
import peaksoft.repository.SubcategoryRepository;
import peaksoft.service.SubcategoryService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubcategorySerImpl implements SubcategoryService {

    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public SimpleResponse save(Long categoryId, SubCategoryRequest subCategoryRequest) {

        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new BadRequestException(
                String.format("Category with id: %d doesn't exist", categoryId)));

        SubCategory subCategory = new SubCategory();
        subCategory.setName(subCategoryRequest.name());

        category.addSubCategory(subCategory);
        subCategory.setCategories(category);

        System.out.println(category);
        subcategoryRepository.save(subCategory);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Sub Category with name: %s successfully SAVED!",subCategoryRequest.name())).build();
    }

    @Override
    public List<SubCategoryResponse> getAllBySubCategory() {
        return subcategoryRepository.getAllSubCategory();

    }

    @Override
    public SubCategoryResponse getSubCategoryById(Long subId) {
        return subcategoryRepository.getByIdSub(subId).orElseThrow(() -> new AlreadyExistsException(
                String.format("Sub Category with id: %d doesn't exist", subId)
        ));
    }

    @Override
    public SimpleResponse update(Long subId, SubCategoryRequest request) {
        SubCategory subCategory = subcategoryRepository.findById(subId).orElseThrow(() -> new NotFoundException(
                String.format("Sub category with Id: %d doesn't exist", subId)
        ));

        if (!subcategoryRepository.existsById(subId)){
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message("Sub Category with id:" + subId + " doesn't exist").build();
        }

        subCategory.setName(request.name());
        subcategoryRepository.save(subCategory);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Sub Category with id: %d successfully UPDATED", subId)).build();
    }

    @Override
    public SimpleResponse delete(Long categoryId, Long subCategoryId) {
        if (!subcategoryRepository.existsById(subCategoryId)){
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(String.format("Sub category with id: %d doesn't exist", subCategoryId)).build();
        }
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new BadCredentialException(
                String.format("Category with id: %d doesn't exist", categoryId)
        ));
        category.getSubcategories().removeIf(c->c.getId().equals(subCategoryId));
        subcategoryRepository.deleteById(subCategoryId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Sub category with id: %d successfully DELETED", subCategoryId)).build();
    }

    @Override
    public SubCategoryPaginationResponse getSubCategoryResponse(int page, int size) {
        PageRequest pageable= PageRequest.of(page-1,size, Sort.by(""));
        Page<SubCategory> subCategories = subcategoryRepository.findAll(pageable);
        SubCategoryPaginationResponse subCategoryPaginationResponse = new SubCategoryPaginationResponse();
        subCategoryPaginationResponse.setSubCategories(subCategories.getContent());
        subCategoryPaginationResponse.setCurrentPage(pageable.getPageNumber()+1);
        subCategoryPaginationResponse.setPageSize(subCategories.getTotalPages());
        return subCategoryPaginationResponse;
    }

}

