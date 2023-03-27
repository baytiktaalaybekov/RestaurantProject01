package peaksoft.dto.response;

import java.math.BigDecimal;

public record MenuItemResponse(
        Long id,
        String name,
        String image,
        int price,
        String description,
        Boolean isVegetarian
) {
}
