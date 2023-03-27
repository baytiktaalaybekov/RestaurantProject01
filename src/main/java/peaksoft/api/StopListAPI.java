package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.StopListRequest;
import peaksoft.dto.response.CategoryPaginationResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.StopListPaginationResponse;
import peaksoft.dto.response.StopListResponse;
import peaksoft.service.StopListService;

import java.rmi.AlreadyBoundException;
import java.util.List;
@RestController
@RequestMapping("/api/stopLists")
@RequiredArgsConstructor
public class StopListAPI {

    private final StopListService stopListService;


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public SimpleResponse saveStopList(@RequestBody StopListRequest request) throws AlreadyBoundException {
        return stopListService.saveStopList(request);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public List<StopListResponse> getAllStopList(){
        return stopListService.getAllStopLists();
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/getById/{id}")
    public StopListResponse getById(@PathVariable Long id){
        return stopListService.getByIdStopList(id);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{id}")
    public SimpleResponse update(@PathVariable Long id,
                                 @RequestBody StopListRequest request){
        return stopListService.update(id, request);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{stopListId}")
    public SimpleResponse delete( @PathVariable Long stopListId){
        return stopListService.delete(stopListId);
    }

    @GetMapping("/pagination")
    public StopListPaginationResponse getStopListResponse(@RequestParam int page,
                                                            @RequestParam int size) {
        return stopListService.getStopListResponse(page, size);

    }

}
