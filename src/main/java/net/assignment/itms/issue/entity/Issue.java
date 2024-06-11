package net.assignment.itms.issue.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.assignment.itms.project.entity.Project;
import net.assignment.itms.user.entity.User;
import net.assignment.itms.utils.Priority;
import net.assignment.itms.utils.Status;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "issue")
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long issue_id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Project project;

    @ManyToOne
    @JoinColumn(nullable = false)
    private IssueType issueType;

    @Column(nullable = false)
    private String Summary;

    @Column(nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn()
    private User reporter;

    @ManyToOne
    @JoinColumn()
    private User assignee;

    @OneToMany(mappedBy = "issue")
    private Set<Comment> comments;

    @Column()
    private LocalDateTime created_at;

    @Column()
    private LocalDateTime updated_at;

    @PrePersist
    protected void onCreate() {
        created_at = LocalDateTime.now();
        updated_at = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updated_at = LocalDateTime.now();
    }
}
