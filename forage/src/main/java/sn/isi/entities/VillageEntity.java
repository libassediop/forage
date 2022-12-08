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
public class VillageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String libelle;
    @ManyToOne
    private ChefDeVillageEntity chefdevillage = new ChefDeVillageEntity();
    @OneToMany(mappedBy = "village")
    private List<ClientEntity> clients = new ArrayList<ClientEntity>();



}
