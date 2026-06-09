package app.model.entity;

import app.model.enums.GameStatus;
import app.model.enums.Genre;
import app.model.enums.Platform;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "games")
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String developer;

    @Column(nullable = false)
    private String publisher;

    private LocalDate releaseDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Platform platform;

    @Digits(integer = 5, fraction = 1)
    @DecimalMin("0.0")
    private BigDecimal hoursPlayed;

    @Digits(integer = 2, fraction = 1)
    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    private BigDecimal rating;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private GameStatus status;

    private boolean favorite;

    @ManyToOne
    @JoinColumn(nullable = false)
    private User owner;

    private LocalDate purchaseDate;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDateTime.now();
    }
}
