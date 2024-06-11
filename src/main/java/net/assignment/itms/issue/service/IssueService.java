package net.assignment.itms.issue.service;

import net.assignment.itms.exception.BadRequestException;
import net.assignment.itms.exception.NotFoundException;
import net.assignment.itms.issue.dto.IssueDetailDto;
import net.assignment.itms.issue.dto.IssueDto;
import net.assignment.itms.issue.dto.UpdateStatusDto;
import net.assignment.itms.issue.entity.Issue;
import net.assignment.itms.user.entity.User;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    List<IssueDetailDto> findAllIssues();
    String createIssue(IssueDto issueDto) throws BadRequestException;

    String assignIssueToDeveloper(User assignee, Long issue_id) throws NotFoundException;

    String updateIssueStatus(UpdateStatusDto updateStatusDto, User user) throws BadRequestException, NotFoundException;
    Issue findById(Long issue_id) throws NotFoundException;
    List<IssueDetailDto> findAllByAssigneeId(User assignee);
}
