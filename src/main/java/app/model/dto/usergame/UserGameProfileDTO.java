package app.model.dto.usergame;

import app.model.dto.game.GenreDTO;
import app.model.dto.game.PlatformDTO;
import app.model.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
public class UserGameProfileDTO {
    private UUID id;
    private UUID gameId;
    private String name;
    private String developer;
    private String publisher;
    private String description;
    private LocalDate releaseDate;
    private Set<GenreDTO> genres = new HashSet<>();
    private Set<PlatformDTO> platforms = new HashSet<>();
    private GameStatus status;
    private BigDecimal hoursPlayed;
    private BigDecimal rating;
    private boolean favorite;
}
