package app.model.dto.usergame;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserGameDTO {

    private UUID userId;
    private List<UserGameItemDTO> games;

}
