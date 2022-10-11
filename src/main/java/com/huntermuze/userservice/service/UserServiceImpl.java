package com.huntermuze.userservice.service;

import com.huntermuze.userservice.dto.User;
import com.huntermuze.userservice.dto.container.Users;
import com.huntermuze.userservice.exception.AlreadyExistException;
import com.huntermuze.userservice.exception.InvalidIdException;
import com.huntermuze.userservice.exception.NotFoundException;
import com.huntermuze.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMergeService userMergeService;

    @Override
    public Users getAllUsers() {
        return new Users(userRepository.findAll().stream().map(pojo -> userMergeService.userEntityToDTO(pojo)).collect(Collectors.toList()));
    }

    @Override
    public User getUser(String id) throws InvalidIdException, NotFoundException {
        long userID;
        try {
            userID = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new InvalidIdException(String.format("This id (%s) is not a valid user id!", id));
        }
        var result = userRepository.findById(userID);
        if (result.isEmpty()) {
            throw new NotFoundException(String.format("The user with id %s was not found!", id));
        }
        return userMergeService.userEntityToDTO(result.get());
    }

    @Override
    public void addUser(User user) throws AlreadyExistException {
        var result = userRepository.findById(user.getId());
        if (result.isPresent()) {
            throw new AlreadyExistException("This user already exists!");
        }
        userRepository.save(userMergeService.userDTOToEntity(user));
    }

    @Override
    public void updateUser(User user) throws NotFoundException {
        var result = userRepository.findById(user.getId());
        if (result.isEmpty()) {
            throw new NotFoundException(String.format("The user with id %s was not found!", user.getId()));
        }
        userRepository.save(userMergeService.userDTOToEntity(user));
    }

    @Override
    public void deleteUser(String id) throws InvalidIdException, NotFoundException {
        long userID;
        try {
            userID = Long.parseLong(id);
        } catch (NumberFormatException e) {
            throw new InvalidIdException(String.format("This id (%s) is not a valid user id!", id));
        }
        var result = userRepository.findById(userID);
        if (result.isEmpty()) {
            throw new NotFoundException(String.format("The user with id %s was not found!", id));
        }
        userRepository.deleteById(userID);
    }
}
