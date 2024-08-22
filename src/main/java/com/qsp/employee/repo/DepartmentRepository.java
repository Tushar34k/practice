package com.qsp.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.employee.model.Department;

public interface DepartmentRepository  extends JpaRepository<Department, Long>{

}
