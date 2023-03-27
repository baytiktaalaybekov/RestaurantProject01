package peaksoft.service;


import peaksoft.dto.request.UserRequest;
import peaksoft.dto.request.UserResumeRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.UserPaginationResponse;
import peaksoft.dto.response.UserResponse;
import peaksoft.dto.response.UserResponses;


import javax.crypto.BadPaddingException;
import java.util.List;

public interface UserService {

    UserResponse authenticate(UserRequest userRequest);

    SimpleResponse save(UserRequest userRequest);

    List<UserResponses> getAllUsers();

    UserResponses getUserResponseById(Long id);

    SimpleResponse update(Long id,UserRequest userRequest);

    SimpleResponse deleteUser(Long Id);

    SimpleResponse resume(UserResumeRequest userResumeRequest) throws BadPaddingException;

    UserPaginationResponse getUserResponse(int page,int size);
}
