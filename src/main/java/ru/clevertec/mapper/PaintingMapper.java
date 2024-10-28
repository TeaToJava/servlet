package ru.clevertec.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.clevertec.domain.PaintingDto;
import ru.clevertec.entity.Painting;

import java.util.List;

@Mapper
public interface PaintingMapper {

    PaintingMapper INSTANCE = Mappers.getMapper(PaintingMapper.class);
    PaintingDto toPainting(Painting painting);

    List<PaintingDto> toPaintings(List<Painting> paintingEntities);

    Painting toEntity(PaintingDto painting);

    List<Painting> toDomains(List<PaintingDto> paintings);
}
