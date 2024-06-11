package net.assignment.itms.project.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.assignment.itms.issue.entity.Issue;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long project_id;

    @Column(nullable = false)
    private String project_name;

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "project")
    private Set<Issue> issues;

    @Column(nullable = false)
    private Boolean is_deleted = false;

}
