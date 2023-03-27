package peaksoft.dto.response;

import java.time.LocalDate;

public record StopListResponse(
        String menuItemName,
        Long id,
        String reason,
        LocalDate date
) {
}
