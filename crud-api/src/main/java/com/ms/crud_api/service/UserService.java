package com.ms.crud_api.service;

import com.ms.crud_api.exception.AlreadyExistException;
import com.ms.crud_api.exception.BadRequestException;
import com.ms.crud_api.exception.NotFoundException;
import com.ms.crud_api.model.entity.UserEntity;
import com.ms.crud_api.model.request.user.UserLoginRequest;
import com.ms.crud_api.model.request.user.UserRegisterRequest;
import com.ms.crud_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity register(UserRegisterRequest req) throws Exception {
        // prepare request to parent's entity
        UserEntity request = req.toEntity();

        // if username already exists then throw error
        if (this.userRepository.existsByUsername(request.getUsername()))
            throw new AlreadyExistException("Username already exists!");

        try {
            // save parent entity
            return this.userRepository.save(request);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public UserEntity update(Long id, UserRegisterRequest req) throws Exception {
        // validate that user has or not
        UserEntity foundUser = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        // validate if username already exists then throw error
        if (!Objects.equals(foundUser.getUsername(), req.getUsername()))
            if (this.userRepository.existsByUsername(req.getUsername()))
                throw new AlreadyExistException("Username already exists!");

        // prepare data
        foundUser.setUsername(req.getUsername());
        if (req.getAddress() == null) foundUser.setAddress(null);
        else foundUser.setAddress(req.getAddress().toEntity(foundUser));

        try {
            // save parent
            return this.userRepository.save(foundUser);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public void delete(Long id) throws Exception {
        try {
            this.userRepository.deleteById(id);
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public UserEntity login(UserLoginRequest req) throws BadRequestException {
        return this.userRepository.findByUsername(req.getUsername()).orElseThrow(() -> new BadRequestException("Invalid username"));
    }

    public List<UserEntity> findAll() {
        return this.userRepository.findAll();
    }

    public UserEntity findOne(Long id) throws NotFoundException {
        return this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    }
}
