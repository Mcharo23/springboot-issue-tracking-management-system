package net.assignment.itms.project.service.impl;

import net.assignment.itms.exception.BadRequestException;
import net.assignment.itms.exception.ConflictException;
import net.assignment.itms.exception.NotFoundException;
import net.assignment.itms.project.dto.ProjectDto;
import net.assignment.itms.project.entity.Project;
import net.assignment.itms.project.mapper.ProjectMapper;
import net.assignment.itms.project.repository.ProjectRepository;
import net.assignment.itms.project.service.ProjectService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;

    public ProjectServiceImpl(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<ProjectDto> findAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return projects
                .stream()
                .map(ProjectMapper::mapToProjectDto)
                .collect(Collectors.toList());
    }

    @Override
    public String createProject(ProjectDto projectDto) throws BadRequestException {

        Project savedProject = projectRepository.save(ProjectMapper.mapToProject(projectDto));

        if (savedProject == null) {
            throw new BadRequestException("Failed to save the project, please try again...");

        }

        return "Project has successfully saved";
    }

    @Override
    public Project findProjectById(Long project_id) throws NotFoundException {
        return projectRepository.findById(project_id).orElseThrow(() -> new NotFoundException("Project not found"));
    }
}
