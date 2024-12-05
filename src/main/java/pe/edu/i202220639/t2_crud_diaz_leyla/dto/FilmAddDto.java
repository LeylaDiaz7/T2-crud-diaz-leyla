package pe.edu.i202220639.t2_crud_diaz_leyla.dto;

import java.util.Date;

public record FilmAddDto(
                         String title,
                         String description,
                         Integer releaseYear,
                         Integer languageId,
                         Integer rentalDuration,
                         Double rentalRate,
                         Integer length,
                         Double replacementCost,
                         String rating,
                         String specialFeatures,
                         Date lastUpdate) {
}
