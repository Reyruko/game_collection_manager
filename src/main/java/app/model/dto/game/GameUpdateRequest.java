package app.model.dto.game;

import app.model.enums.Genre;
import app.model.enums.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameUpdateRequest {

    private String name;
    private Genre genre;
    private Platform platform;
    private BigDecimal hoursPlayed;
    private BigDecimal rating;
    private boolean isFavorite;
}
