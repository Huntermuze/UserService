package com.huntermuze.userservice.repository;

import com.huntermuze.userservice.entity.UserPOJO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserPOJO, String> {
}
