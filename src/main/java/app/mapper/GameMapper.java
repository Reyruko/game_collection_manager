package app.mapper;

import app.model.dto.game.GameDTO;
import app.model.entity.Game;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class GameMapper {

    private final ModelMapper modelMapper;
    public GameMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    public GameDTO toDTO(Game game) {
        return modelMapper.map(game, GameDTO.class);
    }

}
