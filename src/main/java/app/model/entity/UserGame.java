package app.model.entity;

import app.model.enums.GameStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users_games")
public class UserGame {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    private UUID gameId;

    private boolean favorite;

    @Digits(integer = 5, fraction = 1)
    @DecimalMin("0.0")
    private BigDecimal hoursPlayed;

    @Enumerated(EnumType.STRING)
    private GameStatus status;

    private LocalDate addedOn;

    @Digits(integer = 2, fraction = 1)
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    private BigDecimal rating;

    @PrePersist
    public void prePersist() {
        addedOn = LocalDate.now();
    }

}
