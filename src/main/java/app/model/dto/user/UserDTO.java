package app.model.dto.user;

import app.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    private UUID id;
    private String username;
    private String email;
    private UserRole role;
    private String profilePicture;
    private boolean isActive;
    private LocalDate createdOn;
    private LocalDate updatedOn;
    private String bio;

}
