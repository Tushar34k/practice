package com.qsp.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.employee.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
