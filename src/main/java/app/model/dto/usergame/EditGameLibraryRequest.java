package app.model.dto.usergame;

import app.model.enums.GameStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EditGameLibraryRequest {

    public GameStatus status;
    public BigDecimal rating;
    public BigDecimal hoursPlayed;

}
