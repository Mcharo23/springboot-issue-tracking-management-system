package net.assignment.itms.project.controller;

import lombok.Getter;
import net.assignment.itms.exception.BadRequestException;
import net.assignment.itms.project.dto.ProjectDto;
import net.assignment.itms.project.entity.Project;
import net.assignment.itms.project.service.impl.ProjectServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/project")
public class ProjectController {
    private final ProjectServiceImpl projectService;

    public ProjectController(ProjectServiceImpl projectService) {
        this.projectService = projectService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createProject (@RequestBody ProjectDto projectDto) throws BadRequestException {
        String message = projectService.createProject(projectDto);

        Map<String, String> map = new HashMap<>();
        map.put("detail", message);

        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectDto>> getAllProjects () {
        List<ProjectDto> projects = projectService.findAllProjects();
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
}
