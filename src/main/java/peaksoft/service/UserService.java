package peaksoft.service;


import peaksoft.dto.request.UserRequest;
import peaksoft.dto.request.UserResumeRequest;
import peaksoft.dto.respose.SimpleResponse;
import peaksoft.dto.respose.UserResponse;


import javax.crypto.BadPaddingException;
import java.util.List;

public interface UserService {

    UserResponse authenticate(UserRequest userRequest);

    SimpleResponse save(Long restaurantId,UserRequest userRequest);

    List<UserResponse> getAllUsers();

    UserResponse getUserResponseById(Long id);

    SimpleResponse update(Long id,UserRequest userRequest);

    SimpleResponse deleteUser(Long Id);

    SimpleResponse resume(UserResumeRequest userResumeRequest) throws BadPaddingException;

}
