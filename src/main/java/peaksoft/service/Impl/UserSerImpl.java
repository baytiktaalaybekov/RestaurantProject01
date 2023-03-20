package peaksoft.service.Impl;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import peaksoft.config.jwt.JwtUtil;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.request.UserResumeRequest;
import peaksoft.dto.respose.SimpleResponse;
import peaksoft.dto.respose.UserResponse;

import peaksoft.entity.Restaurant;
import peaksoft.entity.User;
import peaksoft.enums.Role;
import peaksoft.repository.RestaurantRepository;
import peaksoft.repository.UserRepository;
import peaksoft.service.UserService;

import javax.crypto.BadPaddingException;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserSerImpl implements UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final RestaurantRepository restaurantRepository;


    @Autowired
    public UserSerImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager,
                       RestaurantRepository restaurantRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public UserResponse authenticate(UserRequest userRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userRequest.email(),
                        userRequest.password()
                )
        );

        User user = userRepository.findByEmail(userRequest.email())
                .orElseThrow(() -> new NoSuchElementException(String.format
                        ("User with email: %s doesn't exists", userRequest.email())));

        String token = jwtUtil.generateToken(user);

        return UserResponse.builder()
                .email(user.getEmail())
                .token(token)
                .build();

    }

    @Override
    public SimpleResponse save(Long restaurantId, UserRequest userRequest) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId).orElseThrow(
                () -> new NoSuchElementException("Restaurant with id: " + restaurantId + " is not found!"));
        User user = new User();
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setRestaurant(restaurant);
        restaurant.addUser(user);
        userRepository.save(user);

        var count = restaurant.getUsers().size();
        if (count > 15) {
            throw new RuntimeException("NETU VAKANSI");
        }

        restaurant.addUser(user);
        user.setRestaurant(restaurant);
        userRepository.save(user);
        restaurant.setNumberOfEmployees((byte) ++count);
        restaurantRepository.save(restaurant);
        return SimpleResponse.builder().status(HttpStatus.OK).
                message(String.format("Employee with fullName : %s job title: %s " +
                                "successfully saved",
                        user.getFirstName().concat(user.getLastName()), user.getRole())).build();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.getAllUsers();
    }

    @Override
    public UserResponse getUserResponseById(Long id) {
        return userRepository.getUsersResponsesById(id);

    }

    @Override
    public SimpleResponse update(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("User with id: " + id + "doesn't exist"));
        user.setEmail(userRequest.email());
        user.setPassword(userRequest.password());
        user.setRole(userRequest.role());
        return SimpleResponse.builder().status(HttpStatus.OK).
                message(String.format("Employee with fullName : %s " +
                                "successfully saved",
                        user.getFirstName().concat(" ").concat(user.getLastName()))).build();
    }

    @Override
    public SimpleResponse deleteUser(Long Id) {
        userRepository.deleteById(Id);
        return SimpleResponse.builder().status(HttpStatus.OK).
                message(String.format("Employee with id : %s " + "deleted", Id)).build();
    }

    @Override
    public SimpleResponse resume(UserResumeRequest userResumeRequest) throws BadPaddingException {
        var age = LocalDate.now().getYear() - userResumeRequest.age().getYear();
        User user = new User();
        user.setFirstName(userResumeRequest.fistName());
        user.setLastName(userResumeRequest.lastName());
        user.setDateOfBirth(userResumeRequest.age());
        user.setEmail(userResumeRequest.email());
        user.setPassword(userResumeRequest.password());
        user.setPhoneNumber(userResumeRequest.phoneNumber());
        user.setExperience(userResumeRequest.experience());
        user.setRole(userResumeRequest.role());
        if (userResumeRequest.role().equals(Role.WAITER)){
            if (age<=18 || age>=30){
                userRepository.save(user);
            }
        } else if (userResumeRequest.role().equals(Role.CHEF)) {
            if (age<=25 || age>=45){
                userRepository.save(user);
            }

        }else {
            throw  new BadPaddingException();
        }
        return SimpleResponse.builder().status(HttpStatus.OK).
                message(String.format("Employee with fullName : %s " +
                                "successfully saved",
                        user.getFirstName().concat(" ").concat(user.getLastName()))).build();
    }

    @PostConstruct
    public void saveAdmin() {
        User user = new User();
        user.setEmail("admin@gmail.com");
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode("admin"));
        if (!userRepository.existsByEmail(user.getEmail())) {
            userRepository.save(user);
        }
    }


}
