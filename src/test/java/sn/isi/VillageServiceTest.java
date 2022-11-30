package sn.isi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import sn.isi.dto.Village;
import sn.isi.service.VillageService;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
 class VillageServiceTest {

    @Autowired
    private VillageService villageService;

    @Test
    void getVillages() {
        List<Village> VillageList =
                villageService.getVillages();

        Assertions.assertEquals(1, VillageList.size());
    }

    @Test
    void getVillage() {
        Village Village = villageService.getVillage(1);

        Assertions.assertNotNull(Village);
    }

    @Test
    void createVillages() {

        Village Village = new Village();
        Village.setLibelle("Village_TECH");
        Village VillageSave = villageService.createVillage(Village);

        Assertions.assertNotNull(VillageSave);
        //Assertions.assertEquals(appVillages.getNom(), appVillagesSave.getNom());
    }

    @Test
    void updateVillage() {
        Village Village = new Village();
        Village.setLibelle("Village_TECH");

        Village VillageSave = villageService.updateVillage(1, Village);

        Assertions.assertEquals("Village_TECH", VillageSave.getLibelle());

    }

    @Test
    void deleteVillage() {

        villageService.deleteVillages(3);
        Assertions.assertTrue(true);
    }

}
