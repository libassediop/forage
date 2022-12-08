package sn.isi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.isi.entities.ChefDeVillageEntity;
import sn.isi.entities.RoleEntity;

public interface IChefDeVillageRepository extends JpaRepository<ChefDeVillageEntity, Integer> {
}
