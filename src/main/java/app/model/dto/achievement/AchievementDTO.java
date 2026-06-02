package app.model.dto.achievement;

import app.model.entity.Game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AchievementDTO {

    private UUID id;
    private String name;
    private String description;
    private boolean unlocked;
    private LocalDateTime unlockedOn;
    private Game game;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
