package net.assignment.itms.issue.controller;

import net.assignment.itms.exception.BadRequestException;
import net.assignment.itms.exception.NotFoundException;
import net.assignment.itms.issue.dto.*;
import net.assignment.itms.issue.entity.Issue;
import net.assignment.itms.issue.entity.IssueType;
import net.assignment.itms.issue.service.impl.IssueServiceImpl;
import net.assignment.itms.issue.service.impl.IssueTypeServiceImpl;
import net.assignment.itms.project.entity.Project;
import net.assignment.itms.project.service.impl.ProjectServiceImpl;
import net.assignment.itms.user.entity.User;
import net.assignment.itms.user.service.impl.UserServiceImpl;
import org.apache.coyote.Response;
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

    public IssueController(IssueServiceImpl issueService, IssueTypeServiceImpl issueTypeService, ProjectServiceImpl projectService, UserServiceImpl userService) {
        this.issueService = issueService;
        this.issueTypeService = issueTypeService;
        this.projectService = projectService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createIssue(@RequestBody IssueDto issueDto) throws BadRequestException, NotFoundException {
        Project project = projectService.findProjectById(issueDto.getProject_id());
        IssueType issueType = issueTypeService.findById(issueDto.getIssue_type_id());
        User assignee = userService.findUser(issueDto.getAssignee_id());

        issueDto.setProject(project);
        issueDto.setIssueType(issueType);
        issueDto.setAssignee(assignee);

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
        User assignee = userService.findUser(assignIssueToDeveloperDto.getAssignee_id());
        String message = issueService.assignIssueToDeveloper(assignee, assignIssueToDeveloperDto.getIssue_id());
        Map<String, String> map = new HashMap<>();
        map.put("detail", message);
        return ResponseEntity.ok(map);
    }

    @PutMapping("/status")
    public ResponseEntity<Map<String, String>> updateIssueStatus(@RequestBody UpdateStatusDto updateStatusDto) throws BadRequestException, NotFoundException {
        User user = userService.findUser(updateStatusDto.getUser_id());

        String message = issueService.updateIssueStatus(updateStatusDto, user);
        Map<String, String> map = new HashMap<>();
        map.put("detail", message);
        return ResponseEntity.ok(map);
    }

    @GetMapping("/{assignee}")
    public ResponseEntity<List<IssueDetailDto>> getAllByAssigneeId(@PathVariable Long assignee) {
        User user = userService.findUser(assignee);

        List<IssueDetailDto> issueDetailDto = issueService.findAllByAssigneeId(user);
        return ResponseEntity.ok(issueDetailDto);
    }

}
