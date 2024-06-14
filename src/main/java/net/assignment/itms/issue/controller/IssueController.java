package net.assignment.itms.issue.controller;

import net.assignment.itms.config.JwtService;
import net.assignment.itms.config.SecurityUtils;
import net.assignment.itms.exception.BadRequestException;
import net.assignment.itms.exception.NotFoundException;
import net.assignment.itms.issue.dto.*;
import net.assignment.itms.issue.entity.IssueType;
import net.assignment.itms.issue.service.impl.IssueServiceImpl;
import net.assignment.itms.issue.service.impl.IssueTypeServiceImpl;
import net.assignment.itms.project.entity.Project;
import net.assignment.itms.project.service.impl.ProjectServiceImpl;
import net.assignment.itms.user.controller.AuthController;
import net.assignment.itms.user.entity.User;
import net.assignment.itms.user.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/issue")
public class IssueController {
    private final IssueServiceImpl issueService;
    private final IssueTypeServiceImpl issueTypeService;
    private final ProjectServiceImpl projectService;
    private final UserServiceImpl userService;
    private final JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public IssueController(IssueServiceImpl issueService, IssueTypeServiceImpl issueTypeService, ProjectServiceImpl projectService, UserServiceImpl userService, JwtService jwtService) {
        this.issueService = issueService;
        this.issueTypeService = issueTypeService;
        this.projectService = projectService;
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createIssue(@RequestBody IssueDto issueDto) throws BadRequestException, NotFoundException {

        Project project = projectService.findProjectById(issueDto.getProject_id());
        IssueType issueType = issueTypeService.findById(issueDto.getIssue_type_id());
        User assignee = userService.findUser(issueDto.getAssignee_id());

        String email = SecurityUtils.getAuthenticatedUserEmail(jwtService);
        User reporter = userService.findUserByEmail(email);

        issueDto.setProject(project);
        issueDto.setIssueType(issueType);
        issueDto.setAssignee(assignee);
        issueDto.setReporter(reporter);

        String message = issueService.createIssue(issueDto);

        Map<String, String> map = new HashMap<>();
        map.put("detail", message);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<IssueDetailDto>> getAllIssue() {
        List<IssueDetailDto> issues = issueService.findAllIssues();
        return ResponseEntity.ok(issues);
    }

    @PutMapping("/assign")
    public ResponseEntity<Map<String, String>> assignIssueToDeveloper(@RequestBody AssignIssueToDeveloperDto assignIssueToDeveloperDto) throws NotFoundException {
        String email = SecurityUtils.getAuthenticatedUserEmail(jwtService);
        User reporter = userService.findUserByEmail(email);
        User assignee = userService.findUser(assignIssueToDeveloperDto.getAssignee_id());

        String message = issueService.assignIssueToDeveloper(assignee, reporter, assignIssueToDeveloperDto.getIssue_id());
        Map<String, String> map = new HashMap<>();
        map.put("detail", message);
        return ResponseEntity.ok(map);
    }

    @PutMapping("/status")
    public ResponseEntity<Map<String, String>> updateIssueStatus(@RequestBody UpdateStatusDto updateStatusDto) throws BadRequestException, NotFoundException {
        String email = SecurityUtils.getAuthenticatedUserEmail(jwtService);
        User user = userService.findUserByEmail(email);

        String message = issueService.updateIssueStatus(updateStatusDto, user);
        Map<String, String> map = new HashMap<>();
        map.put("detail", message);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/assignee")
    public ResponseEntity<List<IssueDetailDto>> getAllByAssigneeId() {
        String email = SecurityUtils.getAuthenticatedUserEmail(jwtService);

        User user = userService.findUserByEmail(email);

        List<IssueDetailDto> issueDetailDto = issueService.findAllByAssigneeId(user);
        return ResponseEntity.ok(issueDetailDto);
    }

}
