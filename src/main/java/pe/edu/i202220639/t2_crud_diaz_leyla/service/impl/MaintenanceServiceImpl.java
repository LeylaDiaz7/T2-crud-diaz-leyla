package pe.edu.i202220639.t2_crud_diaz_leyla.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.i202220639.t2_crud_diaz_leyla.dto.*;
import pe.edu.i202220639.t2_crud_diaz_leyla.entity.Film;
import pe.edu.i202220639.t2_crud_diaz_leyla.entity.Language;
import pe.edu.i202220639.t2_crud_diaz_leyla.repository.*;
import pe.edu.i202220639.t2_crud_diaz_leyla.service.MaintenanceService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MaintenanceServiceImpl implements MaintenanceService {

    @Autowired
    FilmRepository filmRepository;

    @Autowired
    LanguageRepository languageRepository;

    @Autowired
    FilmActorRepository filmActorRepository;

    @Autowired
    FilmCategoryRepository filmCategoryRepository;
    @Autowired
    InventoryRepository inventoryRepository;


    @Override
    public List<FilmDto> findAllFilms() {
        List<FilmDto> films = new ArrayList<FilmDto>();
        Iterable<Film> iterable = filmRepository.findAll();
        iterable.forEach(film -> {
            FilmDto filmDto = new FilmDto(
                    film.getFilmId(),
                    film.getTitle(),
                    film.getLanguage().getName(),
                    film.getRentalDuration(),
                    film.getRentalRate());
            films.add(filmDto);
        });
        return films;
    }

    @Override
    public FilmDetailDto findFilmById(int id) {
        Optional<Film> optional = filmRepository.findById(id);
        return optional.map(film -> new FilmDetailDto(film.getFilmId(),
                film.getTitle(),
                film.getDescription(),
                film.getReleaseYear(),
                film.getLanguage().getLanguageId(),
                film.getLanguage().getName(),
                film.getRentalDuration(),
                film.getRentalRate(),
                film.getLength(),
                film.getReplacementCost(),
                film.getRating(),
                film.getSpecialFeatures(),
                film.getLastUpdate())
        ).orElse(null);
    }

    @Override
    public Boolean updateFilm(FilmDetailDto filmDetailDto) {
        Optional<Film> optional = filmRepository.findById(filmDetailDto.filmId());
        return optional.map(
                film -> {

                    film.setTitle(filmDetailDto.title());
                    film.setDescription(filmDetailDto.description());
                    film.setReleaseYear(filmDetailDto.releaseYear());
                    film.setRentalDuration(filmDetailDto.rentalDuration());
                    film.setRentalRate(filmDetailDto.rentalRate());
                    film.setLength(filmDetailDto.length());
                    film.setReplacementCost(filmDetailDto.replacementCost());
                    film.setRating(filmDetailDto.rating());
                    film.setSpecialFeatures(filmDetailDto.specialFeatures());

                    Language language = languageRepository.findById(filmDetailDto.languageId())
                            .orElseThrow(() -> new IllegalArgumentException("Idioma no válido"));
                    film.setLanguage(language);

                    film.setLastUpdate(new Date());

                    filmRepository.save(film);
                    return true;
                }
        ).orElse(false);
    }


    // FALTA PROBAR
    @Transactional
    @Override
    public Boolean deleteFilm(int filmId) {

        return filmRepository.findById(filmId).map(film -> {
            filmRepository.delete(film);
            return true;
        }).orElse(false);
    }

    @Override
    public Boolean insertFilm(FilmAddDto filmAddDto) {

        Film film = new Film();
        film.setTitle(filmAddDto.title());
        film.setDescription(filmAddDto.description());
        film.setReleaseYear(filmAddDto.releaseYear());
        film.setRentalDuration(filmAddDto.rentalDuration());
        film.setRentalRate(filmAddDto.rentalRate());
        film.setLength(filmAddDto.length());
        film.setReplacementCost(filmAddDto.replacementCost());
        film.setRating(filmAddDto.rating());
        film.setSpecialFeatures(filmAddDto.specialFeatures());
        film.setLastUpdate(new Date());
        Language language = languageRepository.findById(filmAddDto.languageId())
                .orElseThrow(() -> new IllegalArgumentException("Idioma no válido"));
        film.setLanguage(language);
        filmRepository.save(film);
        return true;

    }

    @Override
    public List<LanguageDto> getAllLanguages() {
        List<LanguageDto> languages = new ArrayList<LanguageDto>();
        Iterable<Language> iterable = languageRepository.findAll();
        iterable.forEach(language -> {
            LanguageDto languageDto = new LanguageDto(language.getLanguageId(),
                    language.getName());
            languages.add(languageDto);
        });
        return languages;
    }
}
