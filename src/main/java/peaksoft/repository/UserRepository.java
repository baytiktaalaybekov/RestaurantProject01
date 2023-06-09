package peaksoft.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import peaksoft.dto.response.UserApplicationResponse;
import peaksoft.dto.response.UserResponse;
import peaksoft.dto.response.UserResponses;
import peaksoft.entity.SubCategory;
import peaksoft.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query("select new peaksoft.dto.response.UserResponses(u.id,u.firstName,u.lastName,u.dateOfBirth,u.email,u.password,u.phoneNumber,u.role,u.experience) from User u")
    List<UserResponses> getAllUsers();
    @Query("select new peaksoft.dto.response.UserResponses(u.id,u.firstName,u.lastName,u.dateOfBirth,u.email,u.password,u.phoneNumber,u.role,u.experience) from User u where u.id=?1")
    UserResponses getUsersResponsesById(Long id);
    @Query("select new peaksoft.dto.response.UserResponses(u.id,u.firstName,u.lastName,u.dateOfBirth,u.email,u.password,u.phoneNumber,u.role,u.experience) from User u where u.restaurant.id=null ")
    List<UserResponses> findAllByUserResume();

    @Override
    Page<User> findAll(Pageable pageable);

    @Override
    List<User> findAll(Sort sort);

}