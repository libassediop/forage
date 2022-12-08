package sn.isi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity extends PersonneEntity {

    @Column(length = 100, nullable = false, unique = true)
    private String email;
    @Column(length = 150, nullable = false)
    private String password;
    @Column(length = 1)
    private int etat;
    @ManyToOne
    private RoleEntity role= new RoleEntity();
    @OneToMany(mappedBy ="utilisateur" )
    private List<FactureEntity> factures = new ArrayList<FactureEntity>();
    @OneToMany(mappedBy ="utilisateur" )
    private List<ReglementEntity> reglements = new ArrayList<ReglementEntity>();
}
