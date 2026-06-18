package app.model.dto.user;

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
public class ChangePasswordRequest {

    @NotNull(message = "Current password cannot be empty")
    private String currentPassword;

    @NotNull(message = "Please fill new password")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String newPassword;

    @NotNull(message = "Please confirm password")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String confirmPassword;

}
