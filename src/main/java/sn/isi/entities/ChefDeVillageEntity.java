package sn.isi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChefDeVillageEntity extends PersonneEntity {

    private String fonction;
    @OneToMany(mappedBy ="chefdevillage" )
    private List<VillageEntity> village = new ArrayList<VillageEntity>();
}
