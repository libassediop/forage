package sn.isi.service;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.dao.IFactureRepository;
import sn.isi.dto.Facture;
import sn.isi.exception.EntityNotFoundException;
import sn.isi.exception.RequestException;
import sn.isi.mapping.FactureMapper;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class FactureService {
    private IFactureRepository iFactureRepository;
    private FactureMapper FactureMapper;
    MessageSource messageSource;

    public FactureService(IFactureRepository iFactureRepository, FactureMapper FactureMapper, MessageSource messageSource) {
        this.iFactureRepository = iFactureRepository;
        this.FactureMapper = FactureMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<Facture> getFactures() {
        return StreamSupport.stream(iFactureRepository.findAll().spliterator(), false)
                .map(FactureMapper::toFacture)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Facture getFacture(int id) {
        return FactureMapper.toFacture(iFactureRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("Facture.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Transactional
    public Facture createFacture(Facture Facture) {
        return FactureMapper.toFacture(iFactureRepository.save(FactureMapper.fromFacture(Facture)));
    }

    @Transactional
    public Facture updateFacture(int id, Facture Facture) {
        return iFactureRepository.findById(id)
                .map(entity -> {
                    Facture.setId(id);
                    return FactureMapper.toFacture(
                            iFactureRepository.save(FactureMapper.fromFacture(Facture)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("Facture.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteFactures(int id) {
        try {
            iFactureRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("Facture.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
