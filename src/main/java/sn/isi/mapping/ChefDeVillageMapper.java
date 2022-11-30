package sn.isi.mapping;

import org.mapstruct.Mapper;
import sn.isi.dto.Abonnement;
import sn.isi.dto.ChefDeVillage;
import sn.isi.dto.Facture;
import sn.isi.entities.AbonnementEntity;
import sn.isi.entities.ChefDeVillageEntity;
import sn.isi.entities.FactureEntity;


@Mapper
public interface ChefDeVillageMapper {


    ChefDeVillage toChefDeVillage(ChefDeVillageEntity chefdevillage);
    ChefDeVillageEntity fromChefDeVillage(ChefDeVillage chefDeVillage);



}
