package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "subcategories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subcategory {
    @Id
    @SequenceGenerator(name = "subcategory_id_gen"
            ,sequenceName = "subcategory_id_seq",allocationSize = 1)
    @GeneratedValue(generator = "subcategory_id_gen",strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;


    @OneToMany(mappedBy = "subcategory",cascade = {CascadeType.ALL})
    private List<MenuItem> menuItems;


    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private Category categories;

}
