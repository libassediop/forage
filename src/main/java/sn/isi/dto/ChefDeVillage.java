package sn.isi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChefDeVillage extends Personne {
    @NotNull(message = "L'adresse ne doit pas etre null")
    private String fonction;
}
