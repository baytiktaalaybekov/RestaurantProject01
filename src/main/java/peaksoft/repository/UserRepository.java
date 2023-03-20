package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.dto.respose.UserResponse;

import peaksoft.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

//    @Query("select case when count (u) > 0 then true else false end from User u where u.role = 0")
    Boolean existsByEmail(String email);

    @Query("select new peaksoft.dto.respose.UserResponses(u.id,u.firstName,u.lastName,u.dateOfBirth,u.email,u.password,u.phoneNumber,u.role,u.experience) from User u")
    List<UserResponse> getAllUsers();

    @Query("select new peaksoft.dto.respose.UserResponses(u.id,u.firstName,u.lastName,u.dateOfBirth,u.email,u.password,u.phoneNumber,u.role,u.experience) from User u where u.id=?1")
    UserResponse getUsersResponsesById(Long id);


}