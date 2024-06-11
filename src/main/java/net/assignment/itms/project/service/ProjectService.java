package net.assignment.itms.project.service;

import net.assignment.itms.exception.BadRequestException;
import net.assignment.itms.exception.NotFoundException;
import net.assignment.itms.project.dto.ProjectDto;
import net.assignment.itms.project.entity.Project;

import java.util.List;

public interface ProjectService {
    List<ProjectDto> findAllProjects();
    String createProject(ProjectDto projectDto) throws BadRequestException;
    Project findProjectById(Long project_id) throws NotFoundException;
}
