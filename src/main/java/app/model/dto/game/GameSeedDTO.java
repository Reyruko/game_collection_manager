package app.model.dto.game;

import app.model.enums.Genre;
import app.model.enums.Platform;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class GameSeedDTO {
    private String name;
    private String slug;
    private String developer;
    private String publisher;
    private LocalDate releaseDate;
    private String description;
    private String coverImage;
    private Genre genre;
    private Set<Platform> platforms;
}
