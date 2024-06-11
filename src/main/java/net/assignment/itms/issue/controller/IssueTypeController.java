package net.assignment.itms.issue.controller;

import net.assignment.itms.exception.BadRequestException;
import net.assignment.itms.issue.dto.IssueTypeDto;
import net.assignment.itms.issue.service.impl.IssueTypeServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/issue-type")
public class IssueTypeController {
    private final IssueTypeServiceImpl issueTypeService;

    public IssueTypeController(IssueTypeServiceImpl issueTypeService) {
        this.issueTypeService = issueTypeService;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> createIssueType(@RequestBody IssueTypeDto issueTypeDto) throws BadRequestException {
        String message = issueTypeService.createIssueType(issueTypeDto);
        Map<String, String> map = new HashMap<>();
        map.put("detail", message);
        return new ResponseEntity<>(map, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<IssueTypeDto>> getAllIssueType() {
        List<IssueTypeDto> issueTypeDto = issueTypeService.findAllIssueTypes();
        return ResponseEntity.ok(issueTypeDto);
    }
}
