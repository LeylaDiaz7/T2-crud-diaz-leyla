package pe.edu.i202220639.t2_crud_diaz_leyla.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;


}
