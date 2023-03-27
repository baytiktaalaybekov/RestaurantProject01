package peaksoft.dto.request;

import lombok.Builder;
import peaksoft.enums.Role;


import java.time.LocalDate;

@Builder
public record UserRequest(

        Long restaurantId,

        String firstName,

        String lastName,

        String email,
        
//        @PasswordValidation
        String password,

        LocalDate age,

        String phoneNumber,

        int experience,

        Role role
) {
}
