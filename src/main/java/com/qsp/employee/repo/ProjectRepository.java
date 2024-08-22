package com.qsp.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.employee.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
