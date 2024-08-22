package com.qsp.employee.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.qsp.employee.model.Role;
import com.qsp.employee.repo.RoleRepository;

public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Role getRoleById(Long id) {
		return roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));

	}

}
