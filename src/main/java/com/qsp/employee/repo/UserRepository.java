package com.qsp.employee.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.qsp.employee.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

}
