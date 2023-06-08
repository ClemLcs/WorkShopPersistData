package com.epsi.library.repository;

import com.epsi.library.entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


public interface RoleRepository extends CrudRepository<Role, Long> {
    Optional<Role> findByName(String name);

    @Transactional
    default Role getOrCreateDefaultRole(String defaultRoleName) {
        return findByName(defaultRoleName).orElseGet(() -> {
            Role defaultRole = new Role();
            defaultRole.setName(defaultRoleName);
            return save(defaultRole);
        });
    }
}
