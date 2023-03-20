package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "stopList")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StopList {
    @Id
    @SequenceGenerator(name = "stopList_id_gen"
            ,sequenceName = "stopList_id_seq",allocationSize = 1)
    @GeneratedValue(generator = "stopList_id_gen",strategy = GenerationType.SEQUENCE)
    private Long id;
    private String reason;
    private LocalDate date;

    @OneToOne(mappedBy = "stopList")
    private MenuItem menuItem;

}
