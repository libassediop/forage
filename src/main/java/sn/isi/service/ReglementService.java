package sn.isi.service;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.dao.IReglementRepository;
import sn.isi.dto.Reglement;
import sn.isi.exception.EntityNotFoundException;
import sn.isi.exception.RequestException;
import sn.isi.mapping.ReglementMapper;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ReglementService {
    private IReglementRepository iReglementRepository;
    private ReglementMapper ReglementMapper;
    MessageSource messageSource;

    public ReglementService(IReglementRepository iReglementRepository, ReglementMapper ReglementMapper, MessageSource messageSource) {
        this.iReglementRepository = iReglementRepository;
        this.ReglementMapper = ReglementMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<Reglement> getReglements() {
        return StreamSupport.stream(iReglementRepository.findAll().spliterator(), false)
                .map(ReglementMapper::toReglement)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Reglement getReglement(int id) {
        return ReglementMapper.toReglement(iReglementRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("Reglement.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Transactional
    public Reglement createReglement(Reglement Reglement) {
        return ReglementMapper.toReglement(iReglementRepository.save(ReglementMapper.fromReglement(Reglement)));
    }

    @Transactional
    public Reglement updateReglement(int id, Reglement Reglement) {
        return iReglementRepository.findById(id)
                .map(entity -> {
                    Reglement.setId(id);
                    return ReglementMapper.toReglement(
                            iReglementRepository.save(ReglementMapper.fromReglement(Reglement)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("Reglement.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteReglements(int id) {
        try {
            iReglementRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("Reglement.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
