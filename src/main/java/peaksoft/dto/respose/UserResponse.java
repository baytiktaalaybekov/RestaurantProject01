package peaksoft.dto.respose;

import lombok.Builder;


@Builder
public record UserResponse(
        String email,
        String token

) {
}
