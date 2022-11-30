package sn.isi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.isi.entities.ReglementEntity;
import sn.isi.entities.RoleEntity;

public interface IReglementRepository extends JpaRepository<ReglementEntity, Integer> {
}
