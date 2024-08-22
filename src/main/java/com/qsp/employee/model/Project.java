package com.qsp.employee.model;

import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data

public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	
//	@ManyToMany
//    @JoinTable(
//        name = "user_projects",
//        joinColumns = @JoinColumn(name = "project_id"),
//        inverseJoinColumns = @JoinColumn(name = "user_id")
//    )
//	private Set<User> users;
	
	
	
	@OneToMany(mappedBy = "project")
	List<Task> tasks;

}
