package net.assignment.itms.issue.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "issue_type")
public class IssueType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issue_type_id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "issueType")
    private Set<Issue> issues;
}
