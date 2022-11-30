package sn.isi.service;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.dao.IRoleRepository;
import sn.isi.dto.Role;
import sn.isi.exception.EntityNotFoundException;
import sn.isi.exception.RequestException;
import sn.isi.mapping.RoleMapper;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class RoleService {
    private IRoleRepository iRoleRepository;
    private RoleMapper roleMapper;
    MessageSource messageSource;

    public RoleService(IRoleRepository iRoleRepository, RoleMapper roleMapper, MessageSource messageSource) {
        this.iRoleRepository = iRoleRepository;
        this.roleMapper = roleMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<Role> getRoles() {
        return StreamSupport.stream(iRoleRepository.findAll().spliterator(), false)
                .map(roleMapper::toRole)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Role getRole(int id) {
        return roleMapper.toRole(iRoleRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("role.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Transactional
    public Role createRole(Role role) {
        return roleMapper.toRole(iRoleRepository.save(roleMapper.fromRole(role)));
    }

    @Transactional
    public Role updateRole(int id, Role role) {
        return iRoleRepository.findById(id)
                .map(entity -> {
                    role.setId(id);
                    return roleMapper.toRole(
                            iRoleRepository.save(roleMapper.fromRole(role)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("role.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteRoles(int id) {
        try {
            iRoleRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("role.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }


}
