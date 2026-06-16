package app.model.dto.game;

import app.model.enums.Genre;
import app.model.enums.Platform;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameCreateRequest {

    private String name;
    private String developer;
    private String publisher;
    private LocalDate releaseDate;
    private Genre genre;
    private Platform platform;
    private LocalDate purchaseDate;

}
