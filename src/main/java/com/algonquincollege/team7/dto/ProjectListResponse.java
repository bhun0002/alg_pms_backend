package com.algonquincollege.team7.dto;

import com.algonquincollege.team7.model.Project;
import com.algonquincollege.team7.model.Semester;

import java.time.LocalDateTime;

public record ProjectListResponse(
        Long id,
        String projectName,
        String description,
        Semester semester,
        Boolean ndaRequired,
        Boolean showcaseAllowed,
        LocalDateTime createdAt
) {
    public static ProjectListResponse fromProject(Project project) {
        return new ProjectListResponse(
                project.getId(),
                project.getProjectName(),
                project.getDescription(),
                project.getSemester(),
                project.getNdaRequired(),
                project.getShowcaseAllowed(),
                project.getCreatedAt()
        );
    }
}