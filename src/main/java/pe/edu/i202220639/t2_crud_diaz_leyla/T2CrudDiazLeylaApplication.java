package pe.edu.i202220639.t2_crud_diaz_leyla;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import pe.edu.i202220639.t2_crud_diaz_leyla.dto.FilmDetailDto;
import pe.edu.i202220639.t2_crud_diaz_leyla.dto.FilmDto;
import pe.edu.i202220639.t2_crud_diaz_leyla.entity.Film;
import pe.edu.i202220639.t2_crud_diaz_leyla.repository.FilmRepository;

import java.util.Optional;

@SpringBootApplication
public class T2CrudDiazLeylaApplication implements CommandLineRunner {

	@Autowired
	FilmRepository filmRepository;

	public static void main(String[] args) {
		SpringApplication.run(T2CrudDiazLeylaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("----------------------------");
		System.out.println("----- LISTADO DE FILMS -----");
		System.out.println("----------------------------");
		Iterable<Film> iterable = filmRepository.findAll();
		iterable.forEach((film) -> {
			String message = String.format("%s:%s",film.getFilmId(), film.getTitle());
			System.out.print(message + " - ");
		});

		// ---------------------
		System.out.println(" ");
		System.out.println("----------------------------");
		System.out.println("----- LISTADO DE FILMS (del caché)-----");
		System.out.println("----------------------------");
		Iterable<Film> iterable2 = filmRepository.findAll();
		iterable2.forEach((film) -> {
			String message = String.format("%s:%s",film.getFilmId(), film.getTitle());
			System.out.print(message + " - ");
		});

		// ---------------------
		System.out.println(" ");
		System.out.println("-------------------------------");
		System.out.println("----- INGRESO DE FILM -----");
		System.out.println("-------------------------------");
		Optional<Film> optional = filmRepository.findById(1);
		optional.ifPresentOrElse(
				(film) -> {
					film.setTitle("LOS ARISTOGATOS ");
					filmRepository.save(film);
				},
				() -> {
					System.out.println("el id de esta pelicula no se encuentra registrado");
				}
		);

		// ---------------------
		System.out.println(" ");
		System.out.println("----------------------------");
		System.out.println("----- LISTADO ACTUALIZADO -----");
		System.out.println("----------------------------");
		Iterable<Film> iterable3 = filmRepository.findAll();
		iterable3.forEach((film) -> {
			String message = String.format("%s:%s",film.getFilmId(), film.getTitle());
			System.out.print(message + " - ");
		});

	}


}
