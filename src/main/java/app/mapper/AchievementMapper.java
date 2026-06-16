package app.mapper;

import app.model.dto.achievement.AchievementDTO;
import app.model.entity.Achievement;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AchievementMapper {
    private final ModelMapper modelMapper;

    public AchievementMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Achievement toEntity(AchievementDTO dto) {
        return modelMapper.map(dto, Achievement.class);
    }
}
