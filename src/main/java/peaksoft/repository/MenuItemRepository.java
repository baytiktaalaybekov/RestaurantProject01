package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}