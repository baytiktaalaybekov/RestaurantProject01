package peaksoft.service;

import peaksoft.dto.request.RestaurantRequest;
import peaksoft.dto.response.RestaurantResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;

public interface RestaurantService {
    SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest);
    List<RestaurantResponse> getAllRestaurantResponse();
    RestaurantResponse getRestaurantResponseById(Long restaurantId);
    SimpleResponse updateRestaurant(Long Id,RestaurantRequest restaurantRequest);
    SimpleResponse deleteRestaurantRequest(Long id);
}
