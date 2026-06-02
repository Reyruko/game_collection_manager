package app.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "achievements")
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String name;

    private String description;

    private boolean unlocked;

    private LocalDateTime unlockedOn;

    @ManyToOne
    @JoinColumn(name = "game_id")
    private Game game;

    private LocalDateTime createdOn;

    private LocalDateTime updatedOn;

}
