package com.algonquincollege.team7.dto;

import java.util.List;

public record ProjectListResponseWrapper(
        List<ProjectListResponse> projects,
        int page,
        int size,
        long totalElements,
        int totalPages
) {}
