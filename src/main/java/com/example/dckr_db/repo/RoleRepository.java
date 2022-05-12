package com.example.dckr_db.repo;

import com.example.dckr_db.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
