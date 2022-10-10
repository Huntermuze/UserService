package com.huntermuze.userservice.dto.container;

import com.huntermuze.userservice.dto.User;

import java.util.List;

public class Users {
    private List<User> userList;

    public Users(List<User> userList) {
        this.userList = userList;
    }

    public List<User> getUserList() {
        return userList;
    }
}
