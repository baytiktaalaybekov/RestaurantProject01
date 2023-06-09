package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import peaksoft.entity.Cheque;

import java.time.LocalDate;
import java.util.List;

public interface ChequeRepository extends JpaRepository<Cheque, Long> {

    @Override
    Page<Cheque> findAll(Pageable pageable);

    @Override
    List<Cheque> findAll(Sort sort);


    List<Cheque> findByCreatedAt(LocalDate date);


}