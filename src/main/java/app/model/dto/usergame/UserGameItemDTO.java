package app.model.dto.usergame;

import app.model.enums.GameStatus;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGameItemDTO {

    private UUID gameId;
    private String gameTitle;
    private GameStatus status;
}
