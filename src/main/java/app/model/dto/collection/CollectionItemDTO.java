package app.model.dto.collection;

import app.model.enums.GameStatus;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectionItemDTO {

    private UUID gameId;
    private String gameTitle;
    private GameStatus status;
}
