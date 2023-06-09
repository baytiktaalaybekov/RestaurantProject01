package peaksoft.dto.response;

public record AnswerRequest(
        String message,
        Long userId,
        String restaurantName
) {
}
