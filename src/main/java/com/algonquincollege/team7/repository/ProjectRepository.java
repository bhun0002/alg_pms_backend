package com.algonquincollege.team7.repository;

import com.algonquincollege.team7.model.Project;
import com.algonquincollege.team7.model.Semester;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Page<Project> findByOrganizationId(Long organizationId, Pageable pageable);
    Page<Project> findByOrganizationIdAndSemester(Long organizationId, Semester semester, Pageable pageable);
}
