package sn.isi.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.isi.entities.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Integer> {
}
