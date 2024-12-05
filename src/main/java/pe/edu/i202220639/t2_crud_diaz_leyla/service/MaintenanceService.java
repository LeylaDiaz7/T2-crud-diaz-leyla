package pe.edu.i202220639.t2_crud_diaz_leyla.service;

import pe.edu.i202220639.t2_crud_diaz_leyla.dto.*;

import java.util.List;

public interface MaintenanceService {

    List<FilmDto> findAllFilms();

    FilmDetailDto findFilmById(int id);

    Boolean updateFilm(FilmDetailDto filmDetailDto);

    // LO HARÉ MAÑANA
    Boolean deleteFilm(int filmId);

    Boolean insertFilm(FilmAddDto filmAddDto);

    //_--------
    List<LanguageDto> getAllLanguages();

}
