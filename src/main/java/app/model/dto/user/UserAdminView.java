package app.model.dto.user;

import app.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAdminView {

    private UUID id;
    private String username;
    private boolean active;
    private UserRole role;
    private int gamesCount;

}
