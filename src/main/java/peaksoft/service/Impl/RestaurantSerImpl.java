package peaksoft.service.Impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.RestaurantRequest;
import peaksoft.dto.respose.RestaurantResponse;
import peaksoft.dto.respose.SimpleResponse;

import peaksoft.entity.Restaurant;
import peaksoft.repository.RestaurantRepository;
import peaksoft.service.RestaurantService;

import java.util.List;
import java.util.NoSuchElementException;


@Service
@Transactional
@RequiredArgsConstructor
public class RestaurantSerImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;


    @Override
    public SimpleResponse saveRestaurant(RestaurantRequest restaurantRequest) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.name());
        restaurant.setLocation(restaurantRequest.location());
        restaurant.setRestType(restaurantRequest.restType());
        restaurant.setService(restaurantRequest.service());

        restaurantRepository.save(restaurant);

        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Restaurant with name: %s is successfully saved",restaurantRequest.name())).build();
    }

    @Override
    public List<RestaurantResponse> getAllRestaurantResponse() {
        return restaurantRepository.getAllRestaurantResponse();
    }

    @Override
    public RestaurantResponse getRestaurantResponseById(Long restaurantId) {
        return restaurantRepository.getRestaurantsById(restaurantId);
    }

    @Override
    public SimpleResponse updateRestaurant(Long Id, RestaurantRequest restaurantRequest) {
        Restaurant restaurant = restaurantRepository.findById(Id)
                .orElseThrow(() -> new NoSuchElementException(
                        String.format("Restaurant with id: %d in not", Id)));
        restaurant.setName(restaurantRequest.name());
        restaurant.setLocation(restaurantRequest.location());
        restaurant.setRestType(restaurantRequest.restType());

        restaurantRepository.save(restaurant);
        return SimpleResponse.builder().status(HttpStatus.OK)
                .message(String.format("Restaurant with name: %s successfully updated",restaurantRequest.name()))
                .build();
    }

    @Override
    public SimpleResponse deleteRestaurantRequest(Long id) {
        if (!restaurantRepository.existsById(id)){
            return SimpleResponse.builder()
                    .status(HttpStatus.NOT_FOUND)
                    .message(String.format("Restaurant with id: %s is not found ",id))
                    .build();
        }
        restaurantRepository.deleteById(id);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Restaurant with id: %d si successfully deleted",id))
                .build();
    }
}
