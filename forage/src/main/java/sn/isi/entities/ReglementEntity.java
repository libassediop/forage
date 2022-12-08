package sn.isi.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReglementEntity {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
private double net_a_payer;
private Date date;
@ManyToOne
private FactureEntity facture= new FactureEntity();
@ManyToOne
private UserEntity utilisateur= new UserEntity();


}
