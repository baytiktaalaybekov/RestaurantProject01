package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cheques")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cheque {
    @Id
    @SequenceGenerator(name = "cheque_id_gen"
            ,sequenceName = "cheque_id_seq",allocationSize = 1)
    @GeneratedValue(generator = "cheque_id_gen",strategy = GenerationType.SEQUENCE)
    private Long id;
    private BigDecimal priceAverage;
    private LocalDate createdAt;


    @ManyToOne
    private User users;

    @ManyToMany
    private List<MenuItem> menuItems;

}
