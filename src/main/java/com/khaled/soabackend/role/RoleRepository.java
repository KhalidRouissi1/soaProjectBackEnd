package com.khaled.soabackend.role
        ;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    // You can add custom query methods here if needed
}
