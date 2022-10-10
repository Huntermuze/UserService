package com.huntermuze.userservice.service;

import com.huntermuze.userservice.dto.User;
import com.huntermuze.userservice.dto.container.Users;
import com.huntermuze.userservice.exception.AlreadyExistException;
import com.huntermuze.userservice.exception.NotFoundException;
import com.huntermuze.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMergeService userMergeService;

    public Users getAllUsers() {
        return new Users(userRepository.findAll().stream().map(pojo -> userMergeService.userEntityToDTO(pojo)).collect(Collectors.toList()));
    }

    public User getUser(String id) throws NotFoundException {
        var result = userRepository.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException(String.format("The user with id %s was not found!", id));
        }
        return userMergeService.userEntityToDTO(result.get());
    }

    public void addUser(User user) throws AlreadyExistException {
        var result = userRepository.findById(user.getId());
        if (result.isPresent()) {
            throw new AlreadyExistException("This user already exists!");
        }
        userRepository.save(userMergeService.userDTOToEntity(user));
    }

    public void updateUser(User user) throws NotFoundException {
        var result = userRepository.findById(user.getId());
        if (result.isEmpty()) {
            throw new NotFoundException(String.format("The user with id %s was not found!", user.getId()));
        }
        userRepository.save(userMergeService.userDTOToEntity(user));
    }

    public void deleteUser(String id) throws NotFoundException {
        var result = userRepository.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException(String.format("The user with id %s was not found!", id));
        }
        userRepository.deleteById(id);
    }
}
