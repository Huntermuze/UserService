package com.huntermuze.userservice.dto.container;

import com.huntermuze.userservice.dto.User;

import java.util.List;

public record Users(List<User> userList) {
}
