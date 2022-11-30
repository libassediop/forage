package sn.isi.mapping;

import org.mapstruct.Mapper;

import sn.isi.dto.Village;
import sn.isi.entities.VillageEntity;
@Mapper
public interface VillageMapper {
    Village toVillage(VillageEntity village);
    VillageEntity fromVillage(Village village);
}
