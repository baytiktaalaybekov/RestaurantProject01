package peaksoft.dto.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.entity.SubCategory;
import peaksoft.entity.User;

import java.util.List;
@Getter @Setter
@NoArgsConstructor
public class UserPaginationResponse {
    private List<User> users;
    private int currentPage;
    private int pageSize;
}
