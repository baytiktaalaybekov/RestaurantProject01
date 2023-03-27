package peaksoft.service.Impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.StopListRequest;
import peaksoft.dto.response.CategoryPaginationResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.StopListPaginationResponse;
import peaksoft.dto.response.StopListResponse;
import peaksoft.entity.Category;
import peaksoft.entity.MenuItem;
import peaksoft.entity.StopList;
import peaksoft.exception.BadRequestException;
import peaksoft.exception.NotFoundException;
import peaksoft.repository.MenuItemRepository;
import peaksoft.repository.StopListRepository;
import peaksoft.service.StopListService;


import java.util.List;
import java.util.NoSuchElementException;

@Service

public class StopListSerImpl implements StopListService {

    private final StopListRepository stopListRepository;
    private final MenuItemRepository menuItemRepository;

    public StopListSerImpl(StopListRepository stopListRepository, MenuItemRepository menuItemRepository) {
        this.stopListRepository = stopListRepository;
        this.menuItemRepository = menuItemRepository;
    }


    @Override
    public SimpleResponse saveStopList( StopListRequest request){
        MenuItem menuItem = menuItemRepository.findById(request.menuItemId())
                .orElseThrow(()->
                        new NoSuchElementException("MenuItem with id: "+request.menuItemId()+" not found!"));
    if (stopListRepository.count(request.date(),request.menuItemId()) > 0){
        throw new BadRequestException("StopList already exists for this MenuItem and date!");
        }
            StopList stopList = new StopList();
            stopList.setReason(request.reason());
            stopList.setDate(request.date());
            stopList.setMenuItem(menuItem);
            menuItem.setStopList(stopList);
            stopListRepository.save(stopList);
            return SimpleResponse.builder().status(HttpStatus.OK).message(String.format("Successfully saved %s",menuItem.getName())).build();
    }

    @Override
    public List<StopListResponse> getAllStopLists() {
        return stopListRepository.getAllStopLists();

    }

    @Override
    public StopListResponse getByIdStopList(Long id) {
        return stopListRepository.getByIdStopList(id).orElseThrow(() -> new NotFoundException(String.format("This ID: %s not found! ", id)));
    }

        @Override
    public SimpleResponse update(Long id, StopListRequest request) {
        StopList list = stopListRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("This Id : %s not found", id)));
        list.setReason(request.reason());
        list.setDate(request.date());
        stopListRepository.save(list);
        return SimpleResponse.builder().status(HttpStatus.OK).message("Successfully update!").build();
    }

    @Override
    public SimpleResponse delete(Long stopListId) {
        stopListRepository.deleteById(stopListId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message("delete")
                .build();

    }

    @Override
    public StopListPaginationResponse getStopListResponse(int page, int size) {
        PageRequest pageable= PageRequest.of(page-1,size, Sort.by("priceAverage"));
        Page<StopList> pageStopList = stopListRepository.findAll(pageable);
        StopListPaginationResponse stopListPaginationResponse = new StopListPaginationResponse();
        stopListPaginationResponse.setStopLists(pageStopList.getContent());
        stopListPaginationResponse.setCurrentPage(pageable.getPageNumber()+1);
        stopListPaginationResponse.setPageSize(pageStopList.getTotalPages());
        return stopListPaginationResponse;
    }
}