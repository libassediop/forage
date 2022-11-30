package sn.isi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Personne implements Serializable {


    private int id;
    @NotNull(message = "Le text ne doit pas etre null")
    private String nom;
    @NotNull(message = "Le text ne doit pas etre null")
    private String prenom;
    @NotNull(message = "Le text ne doit pas etre null")
    private String telephone;
}
