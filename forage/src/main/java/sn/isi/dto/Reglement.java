package sn.isi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sn.isi.entities.FactureEntity;
import sn.isi.entities.UserEntity;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reglement {


    private int id;
    @NotNull(message = "Le prix ne doit pas etre null")
    private double net_a_payer;
    private Date date;

}
