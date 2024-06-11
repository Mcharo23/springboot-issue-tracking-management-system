package net.assignment.itms.issue.service;

import net.assignment.itms.exception.BadRequestException;
import net.assignment.itms.exception.NotFoundException;
import net.assignment.itms.issue.dto.IssueTypeDto;
import net.assignment.itms.issue.entity.IssueType;

import java.util.List;

public interface IssueTypeService {
    List<IssueTypeDto> findAllIssueTypes();
    String createIssueType(IssueTypeDto issueTypeDto) throws BadRequestException;
    IssueType findById(Long issue_type_id) throws NotFoundException;
}
