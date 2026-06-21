package app.model.entity;

import app.model.enums.Genre;
import app.model.enums.Platform;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

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

    private String slug;

    @Column(nullable = false)
    private String developer;

    @Column(nullable = false)
    private String publisher;

    private LocalDate releaseDate;

    @Column(length = 3000)
    private String description;

    private String coverImage;

    private BigDecimal hoursPlayed;

    private BigDecimal rating;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ElementCollection(targetClass = Platform.class)
    @Enumerated(EnumType.STRING)
    @Column(name = "platform")
    private Set<Platform> platforms = new HashSet<>();

    private LocalDate createdOn;
    private LocalDate updatedOn;

    @PrePersist
    public void prePersist() {
        createdOn = LocalDate.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedOn = LocalDate.now();
    }

    @OneToMany(mappedBy = "game")
    private List<UserGame> users = new ArrayList<>();
}
