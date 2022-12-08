package sn.isi.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sn.isi.dto.Reglement;
import sn.isi.service.ReglementService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/reglement")
@AllArgsConstructor
public class ReglementControlller {

    private ReglementService reglementService;

    @GetMapping
    public List<Reglement> getReglement() {
        return reglementService.getReglements();
    }

    @GetMapping("/{id}")
    public Reglement getReglement(@PathVariable("id") int id) {
        return reglementService.getReglement(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Reglement createAppReglements(@Valid @RequestBody Reglement reglement) {
        return reglementService.createReglement(reglement);
    }

    @PutMapping("/{id}")
    public Reglement updateReglement(@PathVariable("id") int id, @Valid @RequestBody Reglement reglement) {
        return reglementService.updateReglement(id, reglement);
    }

    @DeleteMapping("/{id}")
    public void deleteReglement(@PathVariable("id") int id)  {reglementService.deleteReglements(id);}

}
