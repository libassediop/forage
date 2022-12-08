package sn.isi.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AbonnementEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(length = 100, nullable = false)
    private String numero;
    @Column(length = 500, nullable = false)
    private String descriptipn;
    private Date date;
    @ManyToOne
    private ClientEntity client= new ClientEntity();
    @ManyToOne
    private CompteurEntity compteur= new CompteurEntity();
}
