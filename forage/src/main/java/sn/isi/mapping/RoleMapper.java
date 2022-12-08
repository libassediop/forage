package sn.isi.mapping;

import org.mapstruct.Mapper;

import sn.isi.dto.Role;
import sn.isi.entities.RoleEntity;
@Mapper
public interface RoleMapper {
    Role toRole(RoleEntity role);
    RoleEntity fromRole(Role role);
}
