package io.turntabl.TCMSProjects.Service.dao;

import io.turntabl.TCMSProjects.Service.models.ProjectTO;

import java.util.List;
import java.util.Map;

public interface ProjectDao {
     List<ProjectTO> getAllProjects();
     List<ProjectTO> getProjectByType(String projectType);
     void addProject(ProjectTO project);
     void deleteProject(Integer projectID);
     void updateProject(Integer projectID, ProjectTO project);
}
