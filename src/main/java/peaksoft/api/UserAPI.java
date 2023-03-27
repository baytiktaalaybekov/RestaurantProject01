package peaksoft.api;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.UserRequest;
import peaksoft.dto.response.*;

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
    public UserResponse login(@RequestBody  UserRequest userRequest) {
        return userService.authenticate(userRequest);
//        @Valid
    }


    @GetMapping
    public List<UserResponses> getAllUserResponse(){
        return userService.getAllUsers();
    }


    @PostMapping()
    public SimpleResponse saveUser( @RequestBody UserRequest userRequest){
       return userService.save( userRequest);
    }

    @GetMapping("/{userResponseId}")
    public UserResponses getUserById(@PathVariable Long userResponseId){
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

    @GetMapping("/pagination")
    public UserPaginationResponse getUserResponse(@RequestParam int page,
                                                        @RequestParam int size) {
        return userService.getUserResponse(page, size);

    }
}
