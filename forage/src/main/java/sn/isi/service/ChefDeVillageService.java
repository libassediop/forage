package sn.isi.service;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.dao.IChefDeVillageRepository;
import sn.isi.dto.ChefDeVillage;
import sn.isi.exception.EntityNotFoundException;
import sn.isi.exception.RequestException;
import sn.isi.mapping.ChefDeVillageMapper;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ChefDeVillageService {
    private IChefDeVillageRepository iChefDeVillageRepository;
    private ChefDeVillageMapper chefDeVillageMapper;
    MessageSource messageSource;

    public ChefDeVillageService(IChefDeVillageRepository iChefDeVillageRepository, ChefDeVillageMapper chefDeVillageMapper, MessageSource messageSource) {
        this.iChefDeVillageRepository = iChefDeVillageRepository;
        this.chefDeVillageMapper = chefDeVillageMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<ChefDeVillage> getChefDeVillages() {
        return StreamSupport.stream(iChefDeVillageRepository.findAll().spliterator(), false)
                .map(chefDeVillageMapper::toChefDeVillage)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ChefDeVillage getChefDeVillage(int id) {
        return chefDeVillageMapper.toChefDeVillage(iChefDeVillageRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("ChefDeVillage.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Transactional
    public ChefDeVillage createChefDeVillage(ChefDeVillage ChefDeVillage) {
        return chefDeVillageMapper.toChefDeVillage(iChefDeVillageRepository.save(chefDeVillageMapper.fromChefDeVillage(ChefDeVillage)));
    }

    @Transactional
    public ChefDeVillage updateChefDeVillage(int id, ChefDeVillage chefDeVillage) {
        return iChefDeVillageRepository.findById(id)
                .map(entity -> {
                    chefDeVillage.setId(id);
                    return chefDeVillageMapper.toChefDeVillage(
                            iChefDeVillageRepository.save(chefDeVillageMapper.fromChefDeVillage(chefDeVillage)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("ChefDeVillage.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteChefDeVillages(int id) {
        try {
            iChefDeVillageRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("ChefDeVillage.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
