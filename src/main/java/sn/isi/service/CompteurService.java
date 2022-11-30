package sn.isi.service;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.dao.ICompteurRepository;
import sn.isi.dto.Compteur;
import sn.isi.exception.EntityNotFoundException;
import sn.isi.exception.RequestException;
import sn.isi.mapping.CompteurMapper;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CompteurService {
    private ICompteurRepository iCompteurRepository;
    private CompteurMapper CompteurMapper;
    MessageSource messageSource;

    public CompteurService(ICompteurRepository iCompteurRepository, CompteurMapper CompteurMapper, MessageSource messageSource) {
        this.iCompteurRepository = iCompteurRepository;
        this.CompteurMapper = CompteurMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<Compteur> getCompteurs() {
        return StreamSupport.stream(iCompteurRepository.findAll().spliterator(), false)
                .map(CompteurMapper::toCompteur)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Compteur getCompteur(int id) {
        return CompteurMapper.toCompteur(iCompteurRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("Compteur.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Transactional
    public Compteur createCompteur(Compteur Compteur) {
        return CompteurMapper.toCompteur(iCompteurRepository.save(CompteurMapper.fromCompteur(Compteur)));
    }

    @Transactional
    public Compteur updateCompteur(int id, Compteur Compteur) {
        return iCompteurRepository.findById(id)
                .map(entity -> {
                    Compteur.setId(id);
                    return CompteurMapper.toCompteur(
                            iCompteurRepository.save(CompteurMapper.fromCompteur(Compteur)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("Compteur.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteCompteurs(int id) {
        try {
            iCompteurRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("Compteur.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
