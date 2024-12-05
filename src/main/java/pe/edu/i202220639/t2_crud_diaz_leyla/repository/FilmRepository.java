package pe.edu.i202220639.t2_crud_diaz_leyla.repository;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;
import pe.edu.i202220639.t2_crud_diaz_leyla.entity.Film;

public interface FilmRepository extends CrudRepository<Film, Integer> {

    @Cacheable(value = "films")
    Iterable<Film> findAll();

}
