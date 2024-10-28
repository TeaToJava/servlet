package ru.clevertec.service;


import ru.clevertec.mapper.PaintingMapper;
import ru.clevertec.domain.PaintingDto;
import ru.clevertec.entity.Painting;
import ru.clevertec.exceptions.PaintingNotFoundException;
import ru.clevertec.repository.PaintingRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public class PaintingService {
    private final PaintingRepository paintingRepository = PaintingRepository.getInstance();
    private static final PaintingService INSTANCE = new PaintingService();

    public static PaintingService getInstance() {
        return INSTANCE;
    }

    public List<PaintingDto> getPaintings() {
        List<Painting> paintingEntities = paintingRepository.getPaintings();
        return PaintingMapper.INSTANCE.toPaintings(paintingEntities);
    }

    public PaintingDto getPaintingById(UUID PaintingId) {
        return PaintingMapper.INSTANCE.toPainting(paintingRepository.getPaintingById(PaintingId)
                .orElseThrow(() -> PaintingNotFoundException.byId(PaintingId)));
    }

    public PaintingDto create(PaintingDto painting) {
        Painting paintingEntity = PaintingMapper.INSTANCE.toEntity(painting);
        return PaintingMapper.INSTANCE.toPainting(paintingRepository.create(paintingEntity));
    }

    public PaintingDto update(UUID paintingId, PaintingDto newPainting) {
        Painting paintingEntity = PaintingMapper.INSTANCE.toEntity(newPainting);
        return PaintingMapper.INSTANCE.toPainting(paintingRepository.update(paintingId, paintingEntity));
    }

    public void delete(UUID paintingId) {
        paintingRepository.delete(paintingId);
    }
}
