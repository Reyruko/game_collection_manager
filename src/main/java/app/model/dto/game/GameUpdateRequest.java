package app.model.dto.game;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
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
public class GameUpdateRequest {

    private String name;

    private String description;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate releaseDate;

    @NotEmpty(message = "Please select at least one genre.")
    private Set<UUID> genreIds = new HashSet<>();

    @NotEmpty(message = "Please select at least one platform.")
    private Set<UUID> platformIds = new HashSet<>();

    private BigDecimal hoursPlayed;
    private BigDecimal rating;
    private boolean favorite;
}
