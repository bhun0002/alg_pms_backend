package com.algonquincollege.team7.dto;

import com.algonquincollege.team7.model.Semester;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProjectEditRequest (
    @NotNull(message = "Project Id is required")
    Long id,

    @NotBlank(message = "Project name is required")
    String projectName,

    @NotBlank(message = "Project description is required")
    String description,

    @NotNull(message = "Project available time is required")
    Integer availableTime,

    String purchasingRequirements,

    @NotNull(message = "Project purchasing requirements is required")
    Boolean ndaRequired,

    @NotNull(message = "Project showcase allowed is required")
    Boolean showcaseAllowed,

    @NotNull(message = "Semester is required")
    Semester semester) {
    } 