package pe.edu.i202220639.t2_crud_diaz_leyla.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer filmId;
    private String title;
    private String description;
    private Integer releaseYear;
    private Integer originalLanguageId;
    private Integer rentalDuration;
    private Double rentalRate;
    private Integer length;
    private Double replacementCost;
    private String rating;
    private String specialFeatures;
    private Date lastUpdate;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    // ------------
    @OneToMany(mappedBy = "film", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<FilmActor> filmActors;

    @OneToMany(mappedBy = "film", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<FilmCategory> filmCategories;

    @OneToMany(mappedBy = "film", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Inventory> inventories;
}