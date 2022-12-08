package sn.isi.mapping;

import org.mapstruct.Mapper;
import sn.isi.dto.Facture;
import sn.isi.dto.Reglement;
import sn.isi.entities.FactureEntity;
import sn.isi.entities.ReglementEntity;

@Mapper
public interface ReglementMapper {

    Reglement toReglement(ReglementEntity reglement);
    ReglementEntity fromReglement(Reglement reglement);

}
