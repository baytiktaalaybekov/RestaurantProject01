package peaksoft.dto.request;

import lombok.Builder;

import java.math.BigDecimal;
@Builder
public record MenuItemRequest(
        String name,
        String image,
        int price,
        String description,
        Boolean isVegetarian,
        Long restId,
        Long subCategoryId
) {
}
