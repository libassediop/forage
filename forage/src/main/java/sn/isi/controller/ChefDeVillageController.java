package sn.isi.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.ChefDeVillage;
import sn.isi.service.ChefDeVillageService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/chefdevillage")
@AllArgsConstructor
public class ChefDeVillageController {

    private ChefDeVillageService chefDeVillageService;

    @GetMapping
    public List<ChefDeVillage> getChefDeVillage() {
        return chefDeVillageService.getChefDeVillages();
    }

    @GetMapping("/{id}")
    public ChefDeVillage getChefDeVillage(@PathVariable("id") int id) {
        return chefDeVillageService.getChefDeVillage(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ChefDeVillage createAppChefDeVillages(@Valid @RequestBody ChefDeVillage chefDeVillage) {
        return chefDeVillageService.createChefDeVillage(chefDeVillage);
    }

    @PutMapping("/{id}")
    public ChefDeVillage updateChefDeVillage(@PathVariable("id") int id, @Valid @RequestBody ChefDeVillage chefDeVillage) {
        return chefDeVillageService.updateChefDeVillage(id, chefDeVillage);
    }

    @DeleteMapping("/{id}")
    public void deleteChefDeVillage(@PathVariable("id") int id)  {chefDeVillageService.deleteChefDeVillages(id);}

}
