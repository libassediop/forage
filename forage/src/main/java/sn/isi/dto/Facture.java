package sn.isi.dto;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.isi.entities.CompteurEntity;
import sn.isi.entities.ReglementEntity;
import sn.isi.entities.UserEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Facture {
     private int id;
    @NotNull(message = "La consommation ne doit pas etre null")
    private double prix_unitaire;
    @NotNull(message = "Le prix ne doit pas etre null")
    private double montant;
    @NotNull(message = "L'etat ne doit pas etre null")
    private boolean statut;
    @NotNull(message = "Le statut ne doit pas etre null")
    private Date date;

}
