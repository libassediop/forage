package sn.isi.service;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
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

public class ChefDeVillageService {
    private IChefDeVillageRepository iChefDeVillageRepository;
    private ChefDeVillageMapper ChefDeVillageMapper;
    MessageSource messageSource;

    public ChefDeVillageService(IChefDeVillageRepository iChefDeVillageRepository, ChefDeVillageMapper ChefDeVillageMapper, MessageSource messageSource) {
        this.iChefDeVillageRepository = iChefDeVillageRepository;
        this.ChefDeVillageMapper = ChefDeVillageMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<ChefDeVillage> getChefDeVillages() {
        return StreamSupport.stream(iChefDeVillageRepository.findAll().spliterator(), false)
                .map(ChefDeVillageMapper::toChefDeVillage)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ChefDeVillage getChefDeVillage(int id) {
        return ChefDeVillageMapper.toChefDeVillage(iChefDeVillageRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("ChefDeVillage.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Transactional
    public ChefDeVillage createChefDeVillage(ChefDeVillage ChefDeVillage) {
        return ChefDeVillageMapper.toChefDeVillage(iChefDeVillageRepository.save(ChefDeVillageMapper.fromChefDeVillage(ChefDeVillage)));
    }

    @Transactional
    public ChefDeVillage updateChefDeVillage(int id, ChefDeVillage ChefDeVillage) {
        return iChefDeVillageRepository.findById(id)
                .map(entity -> {
                    ChefDeVillage.setId(id);
                    return ChefDeVillageMapper.toChefDeVillage(
                            iChefDeVillageRepository.save(ChefDeVillageMapper.fromChefDeVillage(ChefDeVillage)));
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
