package com.qsp.employee.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.qsp.employee.model.Department;
import com.qsp.employee.repo.DepartmentRepository;

public class DepartmentService {
	
	@Autowired
	private DepartmentRepository deaDepartmentRepository;
	
	public Department getDeparementById(Long id)
	{
		return deaDepartmentRepository.findById(id).get();
	}

}
