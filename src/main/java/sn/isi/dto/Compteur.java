package sn.isi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.isi.entities.AbonnementEntity;
import sn.isi.entities.FactureEntity;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Compteur {
    private int id;
    @NotNull(message = "Le releve ne doit pas etre null")
    private int index;
    @NotNull(message = "Le numero ne doit pas etre null")
    private String numero;
    @NotNull(message = "L'etat ne doit pas etre null")
    private int etat;

}
