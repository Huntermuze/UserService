package com.huntermuze.userservice.service;

import com.huntermuze.userservice.dto.User;
import com.huntermuze.userservice.entity.UserPOJO;
import org.springframework.stereotype.Service;

@Service
public class UserMergeService {
    public User userEntityToDTO(UserPOJO userPOJO) {
        return new User(userPOJO.getId(), userPOJO.getName(), userPOJO.getAddress(), userPOJO.getAge(),
                userPOJO.getJob(), userPOJO.getEmail(), userPOJO.getMobileNumber());
    }

    public UserPOJO userDTOToEntity(User userDTO) {
        return new UserPOJO(userDTO.getId(), userDTO.getName(), userDTO.getAddress(), userDTO.getAge(),
                userDTO.getJob(), userDTO.getEmail(), userDTO.getMobileNumber());
    }
}
