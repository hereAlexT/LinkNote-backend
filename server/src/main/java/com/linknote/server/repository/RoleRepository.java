package com.linknote.server.repository;

import com.linknote.server.payload.entity.Role;
import com.linknote.server.payload.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}
