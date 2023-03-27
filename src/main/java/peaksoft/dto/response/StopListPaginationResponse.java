package peaksoft.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.entity.StopList;
import peaksoft.entity.SubCategory;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class StopListPaginationResponse {
    private List<StopList> stopLists;
    private int currentPage;
    private int pageSize;
}
