package com.algonquincollege.team7.service;

import com.algonquincollege.team7.dto.*;
import com.algonquincollege.team7.infra.exception.ApiException;
import com.algonquincollege.team7.model.Project;
import com.algonquincollege.team7.model.UserType;
import com.algonquincollege.team7.repository.ProjectRepository;
import com.algonquincollege.team7.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private UserRepository userRepository;

    public void registerProject(@RequestBody @Valid ProjectRegistrationRequest data) {
        var invalidOrganizationId = !userRepository.existsByIdAndType(
            data.organizationId(), 
            UserType.valueOf("ORGANIZATION")
        );
        
        if (invalidOrganizationId) {
            throw new ApiException(HttpStatus.CONFLICT, "Invalid organization ID");
        }

        var user = userRepository.findById(data.organizationId())
            .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Organization not found"));

        var project = new Project(data, user);
        projectRepository.save(project);
    }

    public ProjectListResponseWrapper getProjectsByOrganizationId(Long organizationId, ProjectListRequest request) {
        var organizationExists = userRepository.existsByIdAndType(organizationId, UserType.valueOf("ORGANIZATION"));
        if (!organizationExists) {
            throw new ApiException(HttpStatus.NOT_FOUND, "Organization not found");
        }

        Pageable pageable = PageRequest.of(
                request.page(),
                request.size(),
                Sort.Direction.fromString(request.sortDirection()),
                request.sortBy()
        );

        Page<Project> projectPage;
        if (request.semesterFilter() != null) {
            projectPage = projectRepository.findByOrganizationIdAndSemester(organizationId, request.semesterFilter(), pageable);
        } else {
            projectPage = projectRepository.findByOrganizationId(organizationId, pageable);
        }

        List<ProjectListResponse> projectResponses = projectPage.getContent().stream()
                .map(ProjectListResponse::fromProject)
                .collect(Collectors.toList());

        return new ProjectListResponseWrapper(
                projectResponses,
                projectPage.getNumber(),
                projectPage.getSize(),
                projectPage.getTotalElements(),
                projectPage.getTotalPages()
        );
    }

    public ProjectListResponseWrapper getAllProjects() {
        List<Project> projects = projectRepository.findAll(); 
        List<ProjectListResponse> projectResponses = projects.stream()
            .map(ProjectListResponse::fromProject)
            .collect(Collectors.toList());

        return new ProjectListResponseWrapper(
            projectResponses, 
            0, 
            projects.size(), 
            projects.size(), 
            1
        );
    }

    public ProjectViewResponse findProjectById(Long projectId) {
        var project = projectRepository.findById(projectId)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Project not found"));

        return new ProjectViewResponse(project);
    }

    public void editProject(@RequestBody @Valid ProjectEditRequest data) {
        var project = projectRepository.findById(data.id())
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "Project not found"));

        project.updateFrom(data);
        projectRepository.save(project);
    }
}