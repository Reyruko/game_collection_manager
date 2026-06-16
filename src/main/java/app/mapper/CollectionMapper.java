package app.mapper;

import app.model.dto.collection.CollectionCreateRequest;
import app.model.entity.Collection;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CollectionMapper {
    private final ModelMapper modelMapper;

    public CollectionMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Collection toEntity(CollectionCreateRequest dto) {
        return modelMapper.map(dto, Collection.class);
    }
}
