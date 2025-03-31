package com.algonquincollege.team7.dto;

import com.algonquincollege.team7.model.Semester;

public record ProjectListRequest(
        Integer page,
        Integer size,
        String sortBy,
        String sortDirection,
        Semester semesterFilter
) {
    public ProjectListRequest {
        if (page == null) page = 0;
        if (size == null) size = 10;
        if (sortBy == null) sortBy = "createdAt";
        if (sortDirection == null) sortDirection = "DESC";
    }
}
