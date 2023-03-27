package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "stopList")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class StopList {
    @Id
    @SequenceGenerator(name = "stopList_id_gen"
            ,sequenceName = "stopList_id_seq",allocationSize = 1)
    @GeneratedValue(generator = "stopList_id_gen",strategy = GenerationType.SEQUENCE)
    private Long id;
    private String reason;
    private LocalDate date;

    @OneToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST})
    private MenuItem menuItem;

}
