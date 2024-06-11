package net.assignment.itms.issue.service.impl;

import net.assignment.itms.exception.BadRequestException;
import net.assignment.itms.exception.NotFoundException;
import net.assignment.itms.issue.dto.CommentDto;
import net.assignment.itms.issue.dto.IssueDetailDto;
import net.assignment.itms.issue.dto.IssueDto;
import net.assignment.itms.issue.dto.UpdateStatusDto;
import net.assignment.itms.issue.entity.Comment;
import net.assignment.itms.issue.entity.Issue;
import net.assignment.itms.issue.mapper.IssueMapper;
import net.assignment.itms.issue.repository.CommentRepository;
import net.assignment.itms.issue.repository.IssueRepository;
import net.assignment.itms.issue.service.IssueService;
import net.assignment.itms.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class IssueServiceImpl implements IssueService {
    private final IssueRepository issueRepository;
    private final CommentRepository commentRepository;

    public IssueServiceImpl(IssueRepository issueRepository, CommentRepository commentRepository) {
        this.issueRepository = issueRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public List<IssueDetailDto> findAllIssues() {
        List<Issue> issues = issueRepository.findAll();
        return issues.stream()
                .map(IssueMapper::mapToIssueDetailDto)
                .collect(Collectors.toList());
    }

    @Override
    public String createIssue(IssueDto issueDto) throws BadRequestException {
        Issue issue = issueRepository.save(IssueMapper.mapToIssue(issueDto));

        if (issue == null) {
            throw new BadRequestException("Failed to save issue, please try again.");
        }

        return "Issue created successfully";
    }

    @Override
    public String assignIssueToDeveloper(User assignee, Long issue_id) throws NotFoundException {
        Issue issue = issueRepository.findById(issue_id)
                .orElseThrow(() -> new NotFoundException("Issue not found"));

        issue.setAssignee(assignee);
        issueRepository.save(issue);

        return "Developer assigned successfully to issue";
    }

    @Override
    public String updateIssueStatus(UpdateStatusDto updateStatusDto, User user) throws BadRequestException, NotFoundException {
        Issue issue = issueRepository.findById(updateStatusDto.getIssue_id()).orElseThrow(() -> new NotFoundException("Issue not found"));
        issue.setStatus(updateStatusDto.getStatus());

        CommentDto commentDto = new CommentDto();
        commentDto.setIssue(issue);
        commentDto.setUser(user);
        commentDto.setComment(updateStatusDto.getComment());

        Comment comment = commentRepository.save(IssueMapper.mapToComment(commentDto));

        if (comment == null) {
            throw new BadRequestException("Failed to save comment");
        }

        issueRepository.save(issue);

        return "Issue status updated successfully";
    }

    @Override
    public Issue findById(Long issue_id) throws NotFoundException {
        return issueRepository.findById(issue_id)
                .orElseThrow(() -> new NotFoundException("Issue not found"));
    }

    @Override
    public List<IssueDetailDto> findAllByAssigneeId(User assignee) {
        List<Issue> issues = issueRepository.findByAssignee(assignee);

        return issues
                .stream()
                .map(IssueMapper::mapToIssueDetailDto)
                .collect(Collectors.toList());
    }
}
