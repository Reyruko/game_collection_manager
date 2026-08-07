package app.model.dto.game;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GameCreateRequest {

    @NotBlank(message = "Game name is required.")
    @Size(min = 2, max = 100, message = "Game name must be between 2 and 100 characters.")
    private String name;

    @NotBlank(message = "Developer is required.")
    @Size(min = 2, max = 100, message = "Developer name must be between 2 and 100 characters.")
    private String developer;

    @NotBlank(message = "Publisher is required.")
    @Size(min = 2, max = 100, message = "Publisher name must be between 2 and 100 characters.")
    private String publisher;

    @NotBlank(message = "Description is required.")
    @Size(min = 10, max = 3000, message = "Description must be between 10 and 3000 characters.")
    private String description;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;

    @NotEmpty(message = "Please select at least one genre.")
    private Set<UUID> genreIds = new HashSet<>();

    @NotEmpty(message = "Please select at least one platform.")
    private Set<UUID> platformIds = new HashSet<>();

}
