package sn.isi.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;




@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client extends Personne {

    @NotNull(message = "L'adresse ne doit pas etre null")
    private String adresse;

}
