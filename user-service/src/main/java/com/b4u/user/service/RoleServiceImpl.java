package com.b4u.user.service;

import com.b4u.user.domain.Role;
import com.b4u.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;
    @Override
    public Optional<Role> findByRoleId(Long id) {
        return roleRepository.findById(id);
    }
}
