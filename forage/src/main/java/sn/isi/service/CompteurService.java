package sn.isi.service;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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

@Service
public class CompteurService {
    private ICompteurRepository iCompteurRepository;
    private CompteurMapper compteurMapper;
    MessageSource messageSource;

    public CompteurService(ICompteurRepository iCompteurRepository, CompteurMapper compteurMapper, MessageSource messageSource) {
        this.iCompteurRepository = iCompteurRepository;
        this.compteurMapper = compteurMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<Compteur> getCompteurs() {
        return StreamSupport.stream(iCompteurRepository.findAll().spliterator(), false)
                .map(compteurMapper::toCompteur)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Compteur getCompteur(int id) {
        return compteurMapper.toCompteur(iCompteurRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("Compteur.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Transactional
    public Compteur createCompteur(Compteur Compteur) {
        return compteurMapper.toCompteur(iCompteurRepository.save(compteurMapper.fromCompteur(Compteur)));
    }

    @Transactional
    public Compteur updateCompteur(int id, Compteur compteur) {
        return iCompteurRepository.findById(id)
                .map(entity -> {
                    compteur.setId(id);
                    return compteurMapper.toCompteur(
                            iCompteurRepository.save(compteurMapper.fromCompteur(compteur)));
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
