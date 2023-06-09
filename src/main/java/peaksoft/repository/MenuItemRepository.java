package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.dto.response.MenuItemResponseSearch;
import peaksoft.entity.Cheque;
import peaksoft.entity.MenuItem;

import java.util.List;
import java.util.Optional;

public interface                                  MenuItemRepository extends JpaRepository<MenuItem, Long> {

    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id, m.name, m.image, m.price, m.description, m.isVegetarian)" +
            " from MenuItem m where m.id=?1")
    Optional<MenuItemResponse> getByMenuId(Long menuId);

    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)from MenuItem m")
    List<MenuItemResponse> getAllMenus();

    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian) from  MenuItem m  order by m.price asc ")
    List<MenuItemResponse>sortByAsc();
    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian) from  MenuItem m  order by m.price desc ")
    List<MenuItemResponse>sortByDesc();

    @Query("SELECT NEW peaksoft.dto.response.MenuItemResponseSearch(mi.id, mi.name,mi.image, mi.price, s.name,c.name) " +
            "FROM MenuItem mi " +
            "JOIN mi.subcategory s " +
            "JOIN s.categories c " +
            "WHERE mi.name LIKE %:keyword% OR c.name LIKE %:keyword% OR s.name LIKE %:keyword%" )
    List<MenuItemResponseSearch> searchMenuItems(String keyword);

    @Override
    Page<MenuItem> findAll(Pageable pageable);

    @Override
    List<MenuItem> findAll(Sort sort);


//
}