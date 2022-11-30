package sn.isi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.isi.entities.RoleEntity;

public interface IRoleRepository extends JpaRepository<RoleEntity, Integer> {
}
