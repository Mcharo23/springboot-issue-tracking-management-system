package net.assignment.itms.issue.mapper;

import net.assignment.itms.issue.dto.*;
import net.assignment.itms.issue.entity.Comment;
import net.assignment.itms.issue.entity.Issue;
import net.assignment.itms.issue.entity.IssueType;

import java.util.HashSet;
import java.util.Set;

public class IssueMapper {
    public static IssueType mapToissueType(IssueTypeDto issueTypeDto) {
        IssueType issueType = new IssueType();
        issueType.setIssue_type_id(issueTypeDto.getIssue_type_id());
        issueType.setName(issueTypeDto.getName());

        return issueType;
    }

    public static IssueTypeDto mapToIssueTypeDto(IssueType issueType) {
        IssueTypeDto issueTypeDto = new IssueTypeDto();
        issueTypeDto.setIssue_type_id(issueType.getIssue_type_id());
        issueTypeDto.setName(issueType.getName());

        return issueTypeDto;
    }

    public static Issue mapToIssue(IssueDto issueDto) {
        Issue issue = new Issue();
        issue.setIssue_id(issueDto.getIssue_id());
        issue.setProject(issueDto.getProject());
        issue.setIssueType(issueDto.getIssueType());
        issue.setSummary(issueDto.getSummary());
        issue.setDescription(issueDto.getDescription());
        issue.setPriority(issueDto.getPriority());
        issue.setStatus(issueDto.getStatus());
        issue.setReporter(issueDto.getReporter());
        issue.setAssignee(issueDto.getAssignee());

        return issue;
    }

    public static IssueDetailDto mapToIssueDetailDto(Issue issue) {
        IssueDetailDto issueDetailDto = new IssueDetailDto();
        issueDetailDto.setIssue_id(issue.getIssue_id());
        issueDetailDto.setProject_id(issue.getProject().getProject_id());
        issueDetailDto.setProject_name(issue.getProject().getProject_name());
        issueDetailDto.setIssue_type_id(issue.getIssueType().getIssue_type_id());
        issueDetailDto.setIssue_type(issue.getIssueType().getName());
        issueDetailDto.setSummary(issue.getSummary());
        issueDetailDto.setDescription(issue.getDescription());
        issueDetailDto.setPriority(issue.getPriority());
        issueDetailDto.setStatus(issue.getStatus());

        if (issue.getReporter() != null) {
            issueDetailDto.setReporter_id(issue.getReporter().getUser_id());
        }

        if (issue.getAssignee() != null) {
            issueDetailDto.setAssignee_id(issue.getAssignee().getUser_id());
            issueDetailDto.setDeveloper(issue.getAssignee().getFirst_name().concat(" ").concat(issue.getAssignee().getLast_name()));
        }

        Set<CommentDetailDto> commentDtos = new HashSet<>();
        if (issue.getComments() != null) {
            for (Comment comment : issue.getComments()) {
                CommentDetailDto commentDto = new CommentDetailDto();
                commentDto.setComment_id(comment.getComment_id());
                commentDto.setComment(comment.getComment());
                commentDto.setUser_id(comment.getUser().getUser_id());
                commentDto.setIssue_id(comment.getIssue().getIssue_id());
                commentDto.setCreated_at(comment.getCreated_at());
                commentDtos.add(commentDto);
            }
        }

        issueDetailDto.setComments(commentDtos);
        issueDetailDto.setCreated_at(issue.getCreated_at());
        issueDetailDto.setUpdated_at(issue.getUpdated_at());

        return issueDetailDto;
    }

    public static Comment mapToComment(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setComment_id(commentDto.getComment_id());
        comment.setUser(commentDto.getUser());
        comment.setIssue(commentDto.getIssue());
        comment.setComment(commentDto.getComment());

        return comment;
    }

    public static CommentDetailDto mapToCommentDetailDto (Comment comment) {
        CommentDetailDto commentDetailDto = new CommentDetailDto();
        commentDetailDto.setComment_id(comment.getComment_id());
        commentDetailDto.setIssue_id(comment.getIssue().getIssue_id());
        commentDetailDto.setUser_id(comment.getUser().getUser_id());
        commentDetailDto.setComment(comment.getComment());
        commentDetailDto.setCreated_at(comment.getCreated_at());

        return commentDetailDto;
    }

}
