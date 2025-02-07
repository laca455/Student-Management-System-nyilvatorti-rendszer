package com.laca455.studentms.service;

import java.util.List;
import java.util.Optional;

import com.laca455.studentms.exception.EmptyInputException;
import com.laca455.studentms.exception.ProjectNotFoundException;
import com.laca455.studentms.model.Project;
import com.laca455.studentms.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class ProjectService {

	private final ProjectRepository projectRepository;

	public ProjectService(ProjectRepository projectRepository) {
		this.projectRepository = projectRepository;
	}

	public List<Project> getAllProjects() {
		return projectRepository.findAll();
	}

	public Project saveProject(@Valid Project project) {
		return projectRepository.save(project);
	}

	public void deleteProjectById(Long id) {
		if (id == 0) {
			throw new EmptyInputException("Adja meg a törölni ivánt ID-t Az nem lehet 0.");
		}
		Optional<Project> checkIfProjecttWithIdExist = projectRepository.findById(id);
		if (checkIfProjecttWithIdExist.isEmpty()) {
			throw new ProjectNotFoundException(
					"A diák nem törölhető mivel azonosítüval rendelkezik: " + id + " nem létezik.");
		}
		projectRepository.deleteById(id);
	}

}
