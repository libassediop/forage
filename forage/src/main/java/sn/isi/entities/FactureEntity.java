package sn.isi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FactureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double prix_unitaire;
    private double montant;
    private boolean statut;
    private Date date;
    @ManyToOne
    private UserEntity utilisateur= new UserEntity();
    @ManyToOne
    private CompteurEntity compteur= new CompteurEntity();
    @OneToMany(mappedBy ="facture" )
    private List<ReglementEntity> reglements = new ArrayList<ReglementEntity>();
}
