package net.assignment.itms.issue.repository;

import net.assignment.itms.issue.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @NonNull
    Optional<Comment> findById(@NonNull Long comment_id);
}
