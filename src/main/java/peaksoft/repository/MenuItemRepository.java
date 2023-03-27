package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.MenuItemResponse;
import peaksoft.entity.Cheque;
import peaksoft.entity.MenuItem;

import java.util.List;
import java.util.Optional;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {

    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id, m.name, m.image, m.price, m.description, m.isVegetarian)" +
            " from MenuItem m where m.id=?1")
    Optional<MenuItemResponse> getByMenuId(Long menuId);

    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)from MenuItem m")
    List<MenuItemResponse> getAllMenus();

    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian) from  MenuItem m  order by m.price asc ")
    List<MenuItemResponse>sortByAsc();
    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian) from  MenuItem m  order by m.price desc ")
    List<MenuItemResponse>sortByDesc();

//    @Query("SELECT new peaksoft.dto.response.menuItem.MenuItemResponseSearch(c.name,s.name,mi.name,mi.image,mi.price) FROM MenuItem  mi join  mi.subCategories s join s.category c where (mi.name ILIKE %:keyWord% OR c.name ILIKE %:keyWord% OR s.name ILIKE %:keyWord%)")


    @Override
    Page<MenuItem> findAll(Pageable pageable);

    @Override
    List<MenuItem> findAll(Sort sort);

    @Query("select new peaksoft.dto.response.MenuItemResponse(m.id,m.name,m.image,m.price,m.description,m.isVegetarian)from MenuItem m")
    MenuItemResponse findByIdResponse(Long aLong);


//
}