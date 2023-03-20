package peaksoft.dto.respose;


import peaksoft.enums.RestType;



public record RestaurantResponse(
        Long id,
        String name,
        String location,
        RestType restType,
        int numberOfUsers,
        int service
) {
}
