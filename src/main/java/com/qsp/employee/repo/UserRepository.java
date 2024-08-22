package com.qsp.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.employee.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
