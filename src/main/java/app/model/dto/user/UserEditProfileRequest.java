package app.model.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEditProfileRequest {

    @Size(min = 3, message = "Username must be at least 3 characters")
    private String username;

    @Email
    private String email;

    private String bio;

    @NotNull(message = "Please enter password")
    private String currentPassword;

    //private String profilePictureUrl;
}
