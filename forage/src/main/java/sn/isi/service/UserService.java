package sn.isi.service;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sn.isi.dao.IUserRepository;
import sn.isi.dto.User;
import sn.isi.exception.EntityNotFoundException;
import sn.isi.exception.RequestException;
import sn.isi.mapping.UserMapper;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {
    private IUserRepository iUserRepository;
    private UserMapper UserMapper;
    MessageSource messageSource;

    public UserService(IUserRepository iUserRepository, UserMapper UserMapper, MessageSource messageSource) {
        this.iUserRepository = iUserRepository;
        this.UserMapper = UserMapper;
        this.messageSource = messageSource;
    }

    @Transactional(readOnly = true)
    public List<User> getUsers() {
        return StreamSupport.stream(iUserRepository.findAll().spliterator(), false)
                .map(UserMapper::toUser)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public User getUser(int id) {
        return UserMapper.toUser(iUserRepository.findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(messageSource.getMessage("User.notfound", new Object[]{id},
                                Locale.getDefault()))));
    }

    @Transactional
    public User createUser(User User) {
        return UserMapper.toUser(iUserRepository.save(UserMapper.fromUser(User)));
    }

    @Transactional
    public User updateUser(int id, User User) {
        return iUserRepository.findById(id)
                .map(entity -> {
                    User.setId(id);
                    return UserMapper.toUser(
                            iUserRepository.save(UserMapper.fromUser(User)));
                }).orElseThrow(() -> new EntityNotFoundException(messageSource.getMessage("User.notfound", new Object[]{id},
                        Locale.getDefault())));
    }

    @Transactional
    public void deleteUsers(int id) {
        try {
            iUserRepository.deleteById(id);
        } catch (Exception e) {
            throw new RequestException(messageSource.getMessage("User.errordeletion", new Object[]{id},
                    Locale.getDefault()),
                    HttpStatus.CONFLICT);
        }
    }
}
