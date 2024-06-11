package net.assignment.itms.project.repository;

import net.assignment.itms.project.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    @NonNull
    Optional<Project> findById(@NonNull Long project_id);
}
