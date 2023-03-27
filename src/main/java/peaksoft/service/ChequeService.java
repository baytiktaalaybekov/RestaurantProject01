package peaksoft.service;

import peaksoft.dto.request.ChequeOneDayWaiterTotalAmountRequest;
import peaksoft.dto.request.ChequeRequest;
import peaksoft.dto.response.ChequePaginationResponse;
import peaksoft.dto.response.ChequeResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;

public interface ChequeService {
    SimpleResponse save(ChequeRequest request);
    List<ChequeResponse> getAll();
    ChequeResponse getById(Long id);
    SimpleResponse update(Long id, ChequeRequest request);
    SimpleResponse deleteById(Long id);
    Double getAllChequesByUser(Long userId);
    Double getAverageSum(Long restId);


    ChequePaginationResponse getChequeResponse(int page,int size);

}
