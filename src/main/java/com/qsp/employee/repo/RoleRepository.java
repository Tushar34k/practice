package com.qsp.employee.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.employee.enums.RoleType;
import com.qsp.employee.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	  Optional<Role> findByName(RoleType roleType);

}
