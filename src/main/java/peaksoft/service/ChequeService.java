package peaksoft.service;

import peaksoft.dto.request.ChequeRequest;
import peaksoft.dto.respose.ChequeResponse;
import peaksoft.dto.respose.SimpleResponse;

import java.util.List;

public interface ChequeService {
    SimpleResponse saveCheque(ChequeRequest request);

    List<ChequeResponse> getAllChequeResponse() ;

    ChequeResponse getChequeResponseById(Long chequeId);

    SimpleResponse updateChequeResponse(Long chequeId, ChequeResponse chequeResponse);

    SimpleResponse deleteChequeResponse(Long chequeId);
}
