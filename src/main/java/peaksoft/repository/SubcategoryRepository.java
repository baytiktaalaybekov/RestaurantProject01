package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.Subcategory;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
}