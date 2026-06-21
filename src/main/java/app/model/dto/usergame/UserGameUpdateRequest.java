package app.model.dto.usergame;

import app.model.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGameUpdateRequest {
    private UUID gameId;
    private GameStatus status;
}
