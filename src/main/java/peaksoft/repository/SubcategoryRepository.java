package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.SubCategoryResponse;
import peaksoft.entity.SubCategory;

import java.util.List;
import java.util.Optional;

public interface SubcategoryRepository extends JpaRepository<SubCategory, Long> {

    @Query("select new peaksoft.dto.response.SubCategoryResponse(s.id, s.name,s.categories.name) from SubCategory s")
    List<SubCategoryResponse> getAllSubCategory();


    @Query("select new peaksoft.dto.response.SubCategoryResponse(s.id, s.name,s.categories.name) from SubCategory s where s.id=?1")
    Optional<SubCategoryResponse> getByIdSub(Long subId);

    @Override
    Page<SubCategory> findAll(Pageable pageable);

    @Override
    List<SubCategory> findAll(Sort sort);


}