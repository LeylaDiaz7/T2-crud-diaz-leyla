package pe.edu.i202220639.t2_crud_diaz_leyla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.edu.i202220639.t2_crud_diaz_leyla.dto.*;
import pe.edu.i202220639.t2_crud_diaz_leyla.service.MaintenanceService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/maintenance")
public class MaintenanceController {

    @Autowired
    MaintenanceService maintenanceService;

    // INICIO
    @GetMapping("/start")
    public String start(Model model) {

        List<FilmDto> films = maintenanceService.findAllFilms();
        model.addAttribute("films", films);
        return "maintenance";
    }

    // DETALLES
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable Integer id, Model model){

        FilmDetailDto filmDetailDto = maintenanceService.findFilmById(id);
        model.addAttribute("filmDetailDto", filmDetailDto);
        return "maintenance-detail";
    }

    // ACTUALIZAR
    @GetMapping("/update/{id}")
    public String update(@PathVariable Integer id, Model model){
        List<LanguageDto> languages = maintenanceService.getAllLanguages();
        FilmDetailDto filmDetailDto = maintenanceService.findFilmById(id);
        model.addAttribute("filmDetailDto", filmDetailDto);
        model.addAttribute("languages", languages);
        return "maintenance-update";
    }

    @PostMapping("/update-confirm")
    public String editConfirm(@ModelAttribute FilmDetailDto filmDetailDto, Model model) {
        maintenanceService.updateFilm(filmDetailDto);
        return "redirect:/maintenance/start";
    }

    // AGREGAR
    @GetMapping("/add")
    public String add(Model model) {
        List<LanguageDto> languages = maintenanceService.getAllLanguages();
        FilmAddDto filmAddDto = new FilmAddDto(
                "",
                "",
                null,
                null,
                null,
                0.0,
                0,
                null,
                "",
                "",
                new Date()
        );

        model.addAttribute("filmAddDto", filmAddDto);
        model.addAttribute("languages", languages);
        return "maintenance-add"; // Vista de formulario para agregar película
    }

    @PostMapping("/add-confirm")
    public String addConfirm(@RequestParam String title,
                             @RequestParam String description,
                             @RequestParam Integer releaseYear,
                             @RequestParam Integer rentalDuration,
                             @RequestParam Double rentalRate,
                             @RequestParam Integer length,
                             @RequestParam Double replacementCost,
                             @RequestParam String rating,
                             @RequestParam String specialFeatures,
                             @RequestParam Integer languageId,
                             @RequestParam String lastUpdate,
                             Model model) {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(lastUpdate);

            FilmAddDto filmAddDto = new FilmAddDto(
                    title, description, releaseYear, languageId,
                    rentalDuration, rentalRate, length, replacementCost, rating,
                    specialFeatures, parsedDate
            );

            boolean success = maintenanceService.insertFilm(filmAddDto);
            if (success) {
                return "redirect:/maintenance/start";
            }
            model.addAttribute("error", "Error al agregar la película");
            return "maintenance-add";

        } catch (ParseException e) {
            model.addAttribute("error", "Formato de fecha inválido. Use 'yyyy-MM-dd'.");
            return "maintenance-add";
        }
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {

        boolean success = maintenanceService.deleteFilm(id);

        if (success) {
            System.out.println("Eliminando " + id);
        } else {
            System.out.println("No se pudo eliminar el film " + id);
        }

        return "redirect:/maintenance/start";

    }
}
