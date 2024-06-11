package net.assignment.itms.project.mapper;

import net.assignment.itms.project.dto.ProjectDto;
import net.assignment.itms.project.entity.Project;

public class ProjectMapper {
    public static ProjectDto mapToProjectDto(Project project){
        ProjectDto projectDto = new ProjectDto();
        projectDto.setProject_id(project.getProject_id());
        projectDto.setProject_name(project.getProject_name());
        projectDto.setDescription(project.getDescription());

        return projectDto;
    }

    public static Project mapToProject(ProjectDto projectDto){
        Project project = new Project();
        project.setProject_id(projectDto.getProject_id());
        project.setProject_name(projectDto.getProject_name());
        project.setDescription(projectDto.getDescription());

        return project;
    }
}
