package com.qsp.employee.model;

import com.qsp.employee.enums.TaskStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Task {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String taskDiscription;
	
	@Enumerated(EnumType.STRING)
	private TaskStatus taskStatus;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User assignUser;
	
	@ManyToOne
    @JoinColumn(name = "project_id")
    private Project project; 
	
	
	

}
