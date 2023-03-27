package peaksoft.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cheques")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Cheque {
    @Id
    @SequenceGenerator(name = "cheque_id_gen"
            ,sequenceName = "cheque_id_seq",allocationSize = 1)
    @GeneratedValue(generator = "cheque_id_gen",strategy = GenerationType.SEQUENCE)
    private Long id;
    private double priceAverage;
    private LocalDate createdAt;
    private double grandTotal;


    @ManyToOne(cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.EAGER)
    private User users;

    @ManyToMany(mappedBy = "cheques",cascade = {CascadeType.REFRESH,CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST},fetch = FetchType.LAZY)
    private List<MenuItem> menuItems;



    public void setUser(User user) {
        this.users=user;
    }

    public void addMenuItem(MenuItem menuItem) {
        if (menuItems == null){
            menuItems = new ArrayList<>();
        }
        menuItems.add(menuItem);
    }
}
