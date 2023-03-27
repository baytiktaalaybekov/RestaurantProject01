package peaksoft.service;

import peaksoft.dto.request.StopListRequest;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.dto.response.StopListPaginationResponse;
import peaksoft.dto.response.StopListResponse;

import java.rmi.AlreadyBoundException;
import java.util.List;

public interface StopListService {
    SimpleResponse saveStopList(StopListRequest request) throws AlreadyBoundException;

    List<StopListResponse> getAllStopLists();

    StopListResponse getByIdStopList(Long id);

    SimpleResponse update(Long id, StopListRequest request);

    SimpleResponse delete(Long stopListId);

    StopListPaginationResponse getStopListResponse(int page,int size);
}
