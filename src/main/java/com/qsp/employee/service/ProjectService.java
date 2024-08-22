package com.qsp.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.qsp.employee.model.Project;
import com.qsp.employee.repo.ProjectRepository;

public class ProjectService {
	
	@Autowired
	private ProjectRepository projectRepository;
	
	
	 public List<Project> getAllProjects() {
	        return projectRepository.findAll();
	    }

	    public Project getProjectById(Long id) {
	        return projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
	    }

	    public Project createProject(Project project) {
	        return projectRepository.save(project);
	    }

	    public Project updateProject(Long id, Project projectDetails) {
	        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
	        project.setName(projectDetails.getName());
	        return projectRepository.save(project);
	    }

	    public void deleteProject(Long id) {
	        Project project = projectRepository.findById(id).orElseThrow(() -> new RuntimeException("Project not found"));
	        projectRepository.delete(project);
	    }
	

}
