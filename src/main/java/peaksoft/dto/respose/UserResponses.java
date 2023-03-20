package peaksoft.dto.respose;

import peaksoft.enums.Role;

import java.time.LocalDate;

public record UserResponses(

        Long id,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        String email,
        String password,
        String phoneNumber,
        Role role,
        int experience

) {
}
