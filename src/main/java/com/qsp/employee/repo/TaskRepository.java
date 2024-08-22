package com.qsp.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.employee.model.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
