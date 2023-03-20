package peaksoft.dto.request;

import lombok.Builder;
import peaksoft.enums.Role;

import java.time.LocalDate;

@Builder
public record UserResumeRequest(
        String fistName,
        String lastName,
        String email,
        LocalDate age,
        String phoneNumber,
        String password ,
        int experience,
        Role role



) {
}
