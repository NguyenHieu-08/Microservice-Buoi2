package com.b4u.user.service;

import com.b4u.user.domain.User;
import com.b4u.user.dto.UserSignUp;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUserId(Long id);
    void save(User user);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    void saveNewUser(UserSignUp userSignUp);
    void saveUserRole(Long userId, Long roleId);
}
