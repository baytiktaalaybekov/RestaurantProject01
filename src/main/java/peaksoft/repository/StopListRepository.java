package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.StopListResponse;
import peaksoft.entity.MenuItem;
import peaksoft.entity.StopList;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface StopListRepository extends JpaRepository<StopList, Long> {


    @Query("select new peaksoft.dto.response.StopListResponse( s.menuItem.name,s.id, s.reason, s.date) from StopList s")
    List<StopListResponse> getAllStopLists();

    @Query("select new peaksoft.dto.response.StopListResponse(s.menuItem.name,s.id, s.reason, s.date) from StopList s where s.id=?1")
    Optional<StopListResponse> getByIdStopList(Long id);

    @Query("select count (*) from StopList s where s.date=:date and s.menuItem.id = :menuItemId ")
    Integer count (LocalDate date,Long menuItemId);

    List<StopList> findByDate(LocalDate date);


    @Override
    Page<StopList> findAll(Pageable pageable);

    @Override
    List<StopList> findAll(Sort sort);
}