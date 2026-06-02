package app.model.dto.game;

import app.model.entity.User;
import app.model.enums.GameStatus;
import app.model.enums.Genre;
import app.model.enums.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDTO {

    private UUID id;
    private String name;
    private String developer;
    private String publisher;
    private LocalDate releaseDate;
    private Genre genre;
    private Platform platform;
    private BigDecimal hoursPlayed;
    private BigDecimal rating;
    private GameStatus status;
    private boolean isFavorite;
    private User owner;
    private LocalDate purchaseDate;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
