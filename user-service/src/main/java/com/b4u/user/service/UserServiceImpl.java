package com.b4u.user.service;

import com.b4u.user.domain.Role;
import com.b4u.user.domain.User;
import com.b4u.user.dto.UserSignUp;
import com.b4u.user.repository.RoleRepository;
import com.b4u.user.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Optional<User> findByUserId(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void saveNewUser(UserSignUp userSignUp) {
        User user = new User();
        BeanUtils.copyProperties(userSignUp, user);
        save(user);
    }

    @Override
    public void saveUserRole(Long userId, Long roleId) {
        User user = findByUserId(userId).get();
        Role role = roleRepository.findById(roleId).get();
        user.getRoles().add(role);
        userRepository.save(user);
    }
}
