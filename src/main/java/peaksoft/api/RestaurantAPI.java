package peaksoft.api;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.RestaurantRequest;
import peaksoft.dto.response.RestaurantResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.service.RestaurantService;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantAPI {

    private final RestaurantService restaurantService;

    @GetMapping
    public List<RestaurantResponse> getAllRestaurant(){
        return restaurantService.getAllRestaurantResponse();
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse saveRestaurant(@RequestBody RestaurantRequest restaurantRequest){
        return restaurantService.saveRestaurant(restaurantRequest);
    }

    @GetMapping("/{restaurantId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public RestaurantResponse getRestaurantById(@PathVariable Long restaurantId){
        return restaurantService.getRestaurantResponseById(restaurantId);
    }

    @PutMapping("/{restaurantId}")
    public SimpleResponse updateRestaurant(@PathVariable Long restaurantId,
                                           @RequestBody RestaurantRequest restaurantRequest){
        return restaurantService.updateRestaurant(restaurantId,restaurantRequest);
    }
    @DeleteMapping("/{restaurantId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public SimpleResponse deleteRestaurant(@PathVariable Long restaurantId){
        return restaurantService.deleteRestaurantRequest(restaurantId);
    }
}
