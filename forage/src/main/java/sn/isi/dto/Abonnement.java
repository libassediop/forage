package sn.isi.dto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.isi.entities.ClientEntity;
import sn.isi.entities.CompteurEntity;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Abonnement {
    private int id;
    @NotNull(message = "Le numero ne doit pas etre null")
    private String numero;
    @NotNull(message = "Le texte ne doit pas etre vide")
    private String descriptipn;
    @NotNull(message = "Le texte ne doit pas etre vide")
    private Date date;

}
