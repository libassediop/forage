package sn.isi.service;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.dao.IClientRepository;
import sn.isi.dto.Client;
import sn.isi.exception.EntityNotFoundException;
import sn.isi.exception.RequestException;
import sn.isi.mapping.ClientMapper;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ClientService {

    private IClientRepository iClientRepository;
    private ClientMapper ClientMapper;
    MessageSource messageSource;

    public ClientService(IClientRepository iClientRepository, ClientMapper ClientMapper, MessageSource messageSource) {
        this.iClientRepository = iClientRepository;
        this.ClientMapper = ClientMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<Client> getClients() {
        return StreamSupport.stream(iClientRepository.findAll().spliterator(), false)
                .map(ClientMapper::toClient)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Client getClient(int id) {
        return ClientMapper.toClient(iClientRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("Client.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Transactional
    public Client createClient(Client Client) {
        return ClientMapper.toClient(iClientRepository.save(ClientMapper.fromClient(Client)));
    }

    @Transactional
    public Client updateClient(int id, Client Client) {
        return iClientRepository.findById(id)
                .map(entity -> {
                    Client.setId(id);
                    return ClientMapper.toClient(
                            iClientRepository.save(ClientMapper.fromClient(Client)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("Client.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteClients(int id) {
        try {
            iClientRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("Client.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
