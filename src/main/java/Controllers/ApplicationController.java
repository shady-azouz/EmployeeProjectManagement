package Controllers;

import Entities.Employee;
import Services.QueryService;
import Services.QueryServiceImplementation;

import javax.management.Query;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.List;

@Path("/app")
public class ApplicationController {
    QueryService queryService = new QueryServiceImplementation();

    @GET
    @Path("/employees")
    public List<Employee> getAllEmployees() {
        return queryService.queryForAllEmployees();
    }

    @GET
    @Path("/employees-in-project/{projectName}")
    public List<?> getEmployeesInProject(@PathParam("projectName") String projectName) {
        return queryService.queryForEmployeesInProject(projectName);
    }

    @PUT
    @Path("/add-employee-to-project/{employeeId},{projectId}")
    public void assignEmployeeToProject(@PathParam("employeId") Integer employeeId, @PathParam("projectId") Integer projectId){
        queryService.addEmployeeToProject(employeeId, projectId);
    }

    @GET
    @Path("/get-employees-not-in-project/{roleName}")
    public List<?> getEmployeesNotInProject(@PathParam("roleName") String roleName){
        return queryService.queryForEmployeesWithRoleNotInProject(roleName);
    }
}
