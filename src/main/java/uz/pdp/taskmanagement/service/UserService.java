package uz.pdp.taskmanagement.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.taskmanagement.domain.request.UserRequest;
import uz.pdp.taskmanagement.entity.UserEntity;
import uz.pdp.taskmanagement.repository.UserRepository;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    public UserEntity findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->  new UsernameNotFoundException("user with this username not found"));
    }

    public UserEntity save(UserRequest request) {
        UserEntity entity = modelMapper.map(request, UserEntity.class);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));
        return userRepository.save(entity);
    }

    public void delete(UUID id) {
        userRepository.deleteById(id);
    }

    public UserEntity findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("user with this id not found"));
    }

    public UserEntity update(UUID id, UserRequest request) {
        UserEntity entity = findById(id);
        UserEntity update = modelMapper.map(request, UserEntity.class);
        update.setId(id);
        update.setPassword(entity.getPassword());
        return userRepository.save(update);
    }


}
