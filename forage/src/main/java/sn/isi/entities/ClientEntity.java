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
public class ClientEntity extends PersonneEntity {

    @Column(name = "adresse")
    private String adresse;
    @ManyToOne
    private VillageEntity village= new VillageEntity();
    @OneToMany(mappedBy ="client" )
    private List<AbonnementEntity> abonnement = new ArrayList<AbonnementEntity>();

}
