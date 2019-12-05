package io.turntabl.TCMSProjects.Service.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.turntabl.TCMSProjects.Service.dao.ProjectDao;
import io.turntabl.TCMSProjects.Service.models.ProjectTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api
@RestController

public class ProjectDaoImpl implements ProjectDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @ApiOperation("Get All Projects")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/projects")
    @Override
    public List<ProjectTO> getAllProjects() {
        return this.jdbcTemplate.query("select * from projects", BeanPropertyRowMapper.newInstance(ProjectTO.class));
    }

    @ApiOperation("Search Project By Type")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/projects/search/{type}")
    @Override
    public List<ProjectTO> getProjectByType(String projectType) {
            return this.jdbcTemplate.query("select * from projects where type like ?",
                    new Object[]{projectType + "%"},
                    BeanPropertyRowMapper.newInstance(ProjectTO.class));
        }

    @ApiOperation("Add a new project")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/projects/addNewProject")
    @Override
    public void addProject(ProjectTO project) {
            this.jdbcTemplate.update(
                    "insert into projects(type, description) values(?,?)",
                    project.getType(), project.getDescription());
    }
    
    @ApiOperation("Delete a project")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/projects/deleteAProject/{id}")
    @Override
    public void deleteProject(Integer projectID) {
            this.jdbcTemplate.update("delete from projects where project_id = ?", projectID);
    }

    @ApiOperation("Update a project")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/projects/updateProject/{id}")
    @Override
    public void updateProject(Integer projectID, ProjectTO project) {
        this.jdbcTemplate.update(
                "update projects set type = ?, description = ? where project_id = ?",
                project.getType(), project.getDescription());
    }
}
