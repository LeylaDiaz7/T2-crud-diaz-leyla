package pe.edu.i202220639.t2_crud_diaz_leyla.dto;

public record FilmDto(Integer filmId,
                      String title,
                      String language,
                      Integer rentalDuration,
                      Double rentalRate) {
}
