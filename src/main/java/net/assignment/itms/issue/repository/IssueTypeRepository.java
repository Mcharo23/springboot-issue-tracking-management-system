package net.assignment.itms.issue.repository;

import net.assignment.itms.issue.entity.IssueType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface IssueTypeRepository extends JpaRepository<IssueType, Long> {
    @NonNull
    Optional<IssueType> findById(@NonNull Long issue_type_id);

    @NonNull
    Optional<IssueType> findByName(@NonNull String name);
}
