package app.model.dto.game;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameDTO {

    private UUID id;
    private UUID gameId;
    private String name;
    private String developer;
    private String publisher;
    private String description;
    private LocalDate releaseDate;
    private Set<GenreDTO> genres = new HashSet<>();
    private Set<PlatformDTO> platforms = new HashSet<>();
    private BigDecimal hoursPlayed;
    private BigDecimal rating;
    private boolean favorite;
    private LocalDate purchaseDate;
    private String coverImage;

}
