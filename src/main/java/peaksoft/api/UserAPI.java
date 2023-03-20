package peaksoft.api;

import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.respose.SimpleResponse;
import peaksoft.dto.respose.UserResponse;

import peaksoft.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserAPI {

    private final UserService userService;

    public UserAPI(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public UserResponse login(@RequestBody UserRequest userRequest) {
        return userService.authenticate(userRequest);

    }


    @GetMapping
    public List<UserResponse> getAllUserResponse(){
        return userService.getAllUsers();
    }


    @PostMapping("/{id}")
    public SimpleResponse saveUser(@PathVariable Long id, @RequestBody UserRequest userRequest){
       return userService.save(id, userRequest);
    }

    @GetMapping("/{userResponseId}")
    public UserResponse getUserById(@PathVariable Long userResponseId){
        return userService.getUserResponseById(userResponseId);
    }

    @PutMapping("/{userResponseId}")
    public SimpleResponse updateResponse(@PathVariable Long userResponseId,
                                         @RequestBody UserRequest userRequest){
        return userService.update(userResponseId,userRequest);
    }
    @DeleteMapping("/{userResponseId}")
    public SimpleResponse deleteUserResponse(@PathVariable Long userResponseId){
        return userService.deleteUser(userResponseId);
    }




}
