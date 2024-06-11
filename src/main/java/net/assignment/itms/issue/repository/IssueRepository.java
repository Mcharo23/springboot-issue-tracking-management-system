package net.assignment.itms.issue.repository;

import net.assignment.itms.issue.entity.Issue;
import net.assignment.itms.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface IssueRepository extends JpaRepository<Issue, Long> {
    @NonNull
    Optional<Issue> findById(@NonNull Long issue_id);
    @NonNull
    List<Issue> findByAssignee(User assignee);
}
