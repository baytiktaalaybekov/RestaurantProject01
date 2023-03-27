package peaksoft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import peaksoft.dto.response.RestaurantResponse;
import peaksoft.entity.Restaurant;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query("select new peaksoft.dto.response.RestaurantResponse(r.id,r.name,r.location,r.restType,r.numberOfUsers,r.service) from Restaurant r")
    List<RestaurantResponse> getAllRestaurantResponse();

    @Query("select new peaksoft.dto.response.RestaurantResponse(r.id,r.name,r.location,r.restType,r.numberOfUsers,r.service) from Restaurant r")
    RestaurantResponse getRestaurantsById(Long restaurantId);


}