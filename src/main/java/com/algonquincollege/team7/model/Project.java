package com.algonquincollege.team7.model;

import com.algonquincollege.team7.dto.ProjectRegistrationRequest;
import com.algonquincollege.team7.dto.ProjectEditRequest;
import lombok.*;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name")
    private String projectName;

    @Column(nullable = false)
    private String description;

    @Column(name = "available_time")
    private Integer availableTime;

    @Column(name = "purchasing_requirements")
    private String purchasingRequirements;

    @Column(name = "nda_required")
    private Boolean ndaRequired;

    @Column(name = "showcase_allowed")
    private Boolean showcaseAllowed;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Semester semester;

    @ManyToOne
    @JoinColumn(name = "organization_id")
    private User organization;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private User professor;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Project(ProjectRegistrationRequest data, User user) {
        this.projectName = data.projectName();
        this.description = data.description();
        this.availableTime = data.availableTime();
        this.purchasingRequirements = data.purchasingRequirements();
        this.ndaRequired = data.ndaRequired();
        this.showcaseAllowed = data.showcaseAllowed();
        this.semester = data.semester();
        this.createdAt = LocalDateTime.now();
        this.organization = user;
    }

    public void updateFrom(ProjectEditRequest data) {
        this.projectName = data.projectName();
        this.description = data.description();
        this.availableTime = data.availableTime();
        this.purchasingRequirements = data.purchasingRequirements();
        this.ndaRequired = data.ndaRequired();
        this.showcaseAllowed = data.showcaseAllowed();
        this.semester = data.semester();
        this.updatedAt = LocalDateTime.now();
    }
}
