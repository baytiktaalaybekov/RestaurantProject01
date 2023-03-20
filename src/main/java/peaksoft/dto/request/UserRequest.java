package peaksoft.dto.request;

import lombok.Builder;
import peaksoft.enums.Role;

import java.time.LocalDate;

@Builder
public record UserRequest(
        String email,
        String password,
        Role role
) {
}
