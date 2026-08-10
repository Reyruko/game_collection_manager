package app.model.dto.game;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlatformCreateRequest {
    private UUID id;
    @NotBlank(message = "Platform name is required")
    private String name;
}
