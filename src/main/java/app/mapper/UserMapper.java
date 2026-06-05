package app.mapper;

import app.model.dto.user.UserDTO;
import app.model.dto.user.UserRegisterRequest;
import app.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    private final ModelMapper modelMapper;

    public UserMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public  User toEntity(UserRegisterRequest dto) {
        return modelMapper.map(dto, User.class);
    }

    public UserDTO toUserDTO(User saved) {
        return modelMapper.map(saved, UserDTO.class);
    }
}
