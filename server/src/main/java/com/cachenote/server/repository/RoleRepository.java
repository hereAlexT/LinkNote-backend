package com.cachenote.server.repository;

import com.cachenote.server.payload.entity.Role;
import com.cachenote.server.payload.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(UserRole name);
}
