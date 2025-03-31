package com.algonquincollege.team7.controller;

import com.algonquincollege.team7.dto.ProjectListRequest;
import com.algonquincollege.team7.dto.ProjectListResponseWrapper;
import com.algonquincollege.team7.dto.ProjectRegistrationRequest;
import com.algonquincollege.team7.dto.ProjectRegistrationResponse;
import com.algonquincollege.team7.dto.ProjectEditRequest;
import com.algonquincollege.team7.dto.ProjectEditResponse;
import com.algonquincollege.team7.dto.ProjectViewResponse;
import com.algonquincollege.team7.service.ProjectService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

@RestController
@RequestMapping("api/project")
@CrossOrigin(origins = "http://localhost:8080")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity registerProject(@RequestBody @Valid ProjectRegistrationRequest data) {

        projectService.registerProject(data);
        var responseOk = new ProjectRegistrationResponse("Project registered successfully");
        return ResponseEntity.ok(responseOk);
    }

    @GetMapping("/organization/{organizationId}")
    public ResponseEntity<ProjectListResponseWrapper> getProjectsByOrganization(
            @PathVariable Long organizationId,
            @ModelAttribute ProjectListRequest request) {

        ProjectListResponseWrapper response = projectService.getProjectsByOrganizationId(organizationId, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<ProjectListResponseWrapper> getAllProjects() {
        ProjectListResponseWrapper response = projectService.getAllProjects();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectViewResponse> getProjectById(@PathVariable Long id) {
        ProjectViewResponse response = projectService.findProjectById(id);
        return ResponseEntity.ok(response);
    }

    @Transactional
    @PutMapping
    public ResponseEntity editProject(@RequestBody @Valid ProjectEditRequest data) {

        projectService.editProject(data);
        var responseOk = new ProjectEditResponse("Project updated successfully");
        return ResponseEntity.ok(responseOk);
    }
}
