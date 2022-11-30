package sn.isi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sn.isi.dto.Role;
import sn.isi.service.RoleService;

import java.time.Clock;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class RoleServiceTest {

    @Autowired
    private RoleService roleService;

    @Test
    void getRoles() {
        List<Role> roleList =
                roleService.getRoles();

        Assertions.assertEquals(1, roleList.size());
    }

    @Test
    void getRole() {
        Role role = roleService.getRole(1);

        Assertions.assertNotNull(role);
    }

    @Test
    void createRoles() {

        Role role = new Role();
        role.setLibelle("ROLE_CLIENT");
        Role roleSave = roleService.createRole(role);

        Assertions.assertNotNull(roleSave);
        //Assertions.assertEquals(appRoles.getNom(), appRolesSave.getNom());
    }

    @Test
    void updateRole() {
        Role role = new Role();
        role.setLibelle("ROLE_TECH");

        Role roleSave = roleService.updateRole(1, role);

        Assertions.assertEquals("ROLE_TECH", roleSave.getLibelle());

    }

    @Test
    void deleteRole() {

        roleService.deleteRoles(3);
        Assertions.assertTrue(true);
    }

    @Test
    void operation() {
        int val1 = 1;
        int val2 = 2;
        int val3 = val1 + val2;
        Assertions.assertEquals(3, val3);
    }
}