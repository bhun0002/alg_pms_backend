package com.algonquincollege.team7.dto;

import com.algonquincollege.team7.model.Project;
import com.algonquincollege.team7.model.Semester;

import java.time.LocalDateTime;

public record ProjectViewResponse(
        Long id,
        String projectName,
        String description,
        Integer availableTime,
        String purchasingRequirements,
        Boolean ndaRequired,
        Boolean showcaseAllowed,
        Semester semester,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public ProjectViewResponse(Project data) {
        this(
                data.getId(),
                data.getProjectName(),
                data.getDescription(),
                data.getAvailableTime(),
                data.getPurchasingRequirements(),
                data.getNdaRequired(),
                data.getShowcaseAllowed(),
                data.getSemester(),
                data.getCreatedAt(),
                data.getUpdatedAt()
        );
    }

}