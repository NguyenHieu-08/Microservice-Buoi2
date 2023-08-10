package com.b4u.user.repository;

import com.b4u.user.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query(value = "SELECT r FROM Role r  WHERE r.id =:id")
    Optional<Role> findById(@Param("id") Long id);
}
