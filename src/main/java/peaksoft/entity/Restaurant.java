package peaksoft.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.RestType;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "restaurant")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {
    @Id
    @SequenceGenerator(name = "restaurant_id_gen"
            ,sequenceName = "restaurant_id_seq",allocationSize = 1)
    @GeneratedValue(generator = "restaurant_id_gen",strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String location;

    @Enumerated(EnumType.STRING)
    private RestType restType;

    @Column(name = "number_of_users")
    private int numberOfUsers;

    private int service;


    @OneToMany(mappedBy = "restaurant")
    private List<User> users = new ArrayList<>();


    @OneToMany(mappedBy = "restaurant")
    private List <MenuItem> menuItems=new ArrayList<>();

    public void addUser(User user) {
        this.users.add(user);
    }

    public void setNumberOfEmployees(byte b) {
    }
}
