package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Category {
    @Id
    @SequenceGenerator(name = "category_id_gen"
            ,sequenceName = "category_id_seq",allocationSize = 1)
    @GeneratedValue(generator = "category_id_gen",strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;

    @OneToMany(mappedBy = "categories",cascade = {ALL},fetch = FetchType.EAGER)
    private List<SubCategory> subcategories=new ArrayList<>();


    public void addSubCategory(SubCategory subCategory)
    {
        subcategories.add(subCategory);
    }
}
