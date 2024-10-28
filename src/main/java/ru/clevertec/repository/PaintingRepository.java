package ru.clevertec.repository;

import ru.clevertec.entity.Painting;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class PaintingRepository {

    private static final PaintingRepository INSTANCE = new PaintingRepository();

    public static PaintingRepository getInstance() {
        return INSTANCE;
    }

    private static final List<Painting> db = List.of(
            new Painting().setId(UUID.fromString("c84467dc-ebc1-4ea9-b46f-8db4905b3876"))
                    .setTitle("Title 1")
                    .setArtist("Artist 1"),

            new Painting().setId(UUID.fromString("61caa5e4-c3d8-464a-bd40-7e33e8fbc8a5"))
                    .setTitle("Title 2")
                    .setArtist("Artist 2")
    );

    public List<Painting> getPaintings() {
        return db;
    }
    public Optional<Painting> getPaintingById(UUID paintingId) {
        return db.stream()
                .filter(painting -> painting.getId().equals(paintingId))
                .findAny();
    }

    public Painting create(Painting paintingEntity) {
        return paintingEntity;
    }

    public Painting update(UUID paintingId, Painting newPaintingEntity) {
        return newPaintingEntity.setId(paintingId);
    }

    public void delete(UUID paintingId) {

    }
}
