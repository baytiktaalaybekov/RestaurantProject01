package peaksoft.dto.response;


import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import peaksoft.enums.RestType;



public record RestaurantResponse(
        Long id,
        String name,
        String location,
        @Enumerated(EnumType.STRING)
        RestType restType,
        int numberOfUsers,
        int service
) {
}
