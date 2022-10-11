package com.huntermuze.userservice.service;

import com.huntermuze.userservice.dto.User;
import com.huntermuze.userservice.dto.container.Users;
import com.huntermuze.userservice.exception.AlreadyExistException;
import com.huntermuze.userservice.exception.InvalidIdException;
import com.huntermuze.userservice.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Users getAllUsers();

    User getUser(String id) throws InvalidIdException, NotFoundException;

    void addUser(User user) throws AlreadyExistException;

    void updateUser(User user) throws NotFoundException;

    void deleteUser(String id) throws InvalidIdException, NotFoundException;
}
