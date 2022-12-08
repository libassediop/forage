package sn.isi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.isi.entities.FactureEntity;
import sn.isi.entities.ReglementEntity;
import sn.isi.entities.RoleEntity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class User extends Personne {

    @NotNull(message = "L'email ne doit pas etre null")
    private String email;
    @NotNull(message = "Le mot de passe ne doit pas etre null")
    private String password;
    @NotNull(message = "L'etat ne doit pas etre null")
    private int etat;



}
