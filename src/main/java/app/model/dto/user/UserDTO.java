package app.model.dto.user;

import app.model.enums.UserRole;
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
public class UserDTO {

    private UUID id;
    private String username;
    private String gamerTag;
    private String email;
    private String password;
    private UserRole role;
    private String profilePicture;
    private boolean isActive;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;

}
