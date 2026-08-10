package app.model.dto.game;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreCreateRequest {
    private UUID id;
    @NotBlank(message = "Genre name is required")
    private String name;
}
