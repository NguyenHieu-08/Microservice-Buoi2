package com.b4u.user.service;

import com.b4u.user.domain.Role;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByRoleId(Long id);
}
