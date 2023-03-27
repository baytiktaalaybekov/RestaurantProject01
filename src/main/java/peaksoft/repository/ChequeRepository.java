package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.response.ChequeResponse;
import peaksoft.entity.Cheque;
import peaksoft.entity.MenuItem;

import java.math.BigDecimal;
import java.util.List;

public interface ChequeRepository extends JpaRepository<Cheque, Long> {

//    List<ChequeResponse> getAllChequeResponse();

    @Override
    Page<Cheque> findAll(Pageable pageable);

    @Override
    List<Cheque> findAll(Sort sort);

    @Query("select avg(c.grandTotal) from Cheque c where c.users.restaurant.id=:restId")
    Double getAverageSum(Long restId);


}