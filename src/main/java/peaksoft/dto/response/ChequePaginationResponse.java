package peaksoft.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.entity.Cheque;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
public class ChequePaginationResponse {
    private List<Cheque> chequeList;
    private int currentPage;
    private int pageSize;
}
