package app.model.dto.usergame;

import app.model.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@Getter
@Setter
@AllArgsConstructor
public class UserGameProfileDTO {
    private UUID id;
    private UUID gameId;
    private String name;
    private GameStatus status;
    private BigDecimal hoursPlayed;
    private BigDecimal rating;
    private boolean favorite;
}
