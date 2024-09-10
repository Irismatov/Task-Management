package uz.pdp.taskmanagement.service;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.taskmanagement.domain.request.UserRequest;
import uz.pdp.taskmanagement.domain.response.UserResponse;
import uz.pdp.taskmanagement.entity.CompanyEntity;
import uz.pdp.taskmanagement.entity.UserEntity;
import uz.pdp.taskmanagement.entity.enumerators.UserRole;
import uz.pdp.taskmanagement.repository.UserRepository;

import java.util.List;
import java.util.Optional;
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


    public void updateCeo(CompanyEntity company, UserEntity user) {
        user.setCompany(company);
        userRepository.save(user);
    }

    public UserEntity updateUser(UserEntity user) {
        return userRepository.save(user);
    }

    public List<UserResponse> findByRole(UserRole userRole) {
        List<UserEntity> byRole = userRepository.findByRole(userRole);
        return byRole.stream().map(user -> modelMapper.map(user, UserResponse.class)).toList();
    }

    public List<UserResponse> getAllTeamLeads() {
        List<UserEntity> teamLead = userRepository.getAllByRoleAndTeamIsNull(UserRole.TEAM_LEAD);
        return  teamLead.stream().map(user -> modelMapper.map(user, UserResponse.class)).toList();
    }


    public List<UserResponse> getAllTeamScrumMasters() {
        List<UserEntity> teamLead = userRepository.getAllByRoleAndTeamIsNull(UserRole.SCRUM_MASTER);
        return  teamLead.stream().map(user -> modelMapper.map(user, UserResponse.class)).toList();
    }


    public List<UserResponse> getAllProductOwnersAndProductIsNull(){
        List<UserEntity> list = userRepository.getAllByRoleAndProductIsNull(UserRole.PRODUCT_OWNER);
        return  list.stream().map(user -> modelMapper.map(user, UserResponse.class)).toList();
    }

}
