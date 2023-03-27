package peaksoft.dto.request;

import lombok.Builder;
import org.aspectj.bridge.Message;
import org.hibernate.annotations.NotFound;
import org.jetbrains.annotations.NotNull;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Builder
public record ChequeRequest(
//        @NotNull(message="User id shouldn't be null")
//        @Positive(message = "user id should be positive number!")
        Long userId,


//        @NotNull(message = "Menu items id shouldn't be null!")
//        @Positive(message = "menu items id should be positive number!")
        List<Long> menuItemId
) {
}
