package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "menuItems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MenuItem {
    @Id
    @SequenceGenerator(name = "menuItem_id_gen"
            ,sequenceName = "menuItem_id_seq",allocationSize = 1)
    @GeneratedValue(generator = "menuItem_id_gen",strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String image;
    private BigDecimal price;
    private String description;
    private String isVegetarian;

    @ManyToMany(mappedBy = "menuItems")
    private List<Cheque> cheques;

    @ManyToOne
    private Restaurant restaurant;


    @OneToOne
    private StopList stopList;

   @ManyToOne
    private Subcategory subcategory;


}
