package app.mapper;

import app.model.dto.usergame.UserGameCreateRequest;
import app.model.entity.UserGame;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserGameMapper {
    private final ModelMapper modelMapper;

    public UserGameMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public UserGame toEntity(UserGameCreateRequest dto) {
        return modelMapper.map(dto, UserGame.class);
    }
}
