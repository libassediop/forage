package sn.isi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sn.isi.dto.Compteur;
import sn.isi.dto.Role;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompteurServiceTest {

    @Autowired
    private CompteurService compteurService;

    @Test
    void getCompteurs() {
        List<Compteur> roleList =
                compteurService.getCompteurs();

        Assertions.assertEquals(1, roleList.size());
    }

    @Test
    void getCompteur() {
    }

    @Test
    void createCompteur() {
    }

    @Test
    void updateCompteur() {
    }

    @Test
    void deleteCompteurs() {
    }
}