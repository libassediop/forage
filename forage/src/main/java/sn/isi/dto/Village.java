package sn.isi.dto;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.isi.entities.ChefDeVillageEntity;
import sn.isi.entities.ClientEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Village {
    private int id;
    @NotNull(message = "Le nom ne doit pas etre null")
    private String libelle;


}
