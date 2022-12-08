package sn.isi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompteurEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String numero;
    private int etat;
    @Column(name = "indexo")
    private int index;
    @OneToMany(mappedBy ="compteur" )
    private List<AbonnementEntity> abonnements = new ArrayList<AbonnementEntity>();
    @OneToMany(mappedBy ="compteur" )
    private List<FactureEntity> factures = new ArrayList<FactureEntity>();
}
