package Controllers;

import Entities.Employee;
import Services.QueryService;
import Services.QueryServiceImpl;

import javax.json.bind.annotation.JsonbTransient;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/controller")
public class ApplicationController {
    QueryService queryService = new QueryServiceImpl();

    @GET
    @Path("/employees")
    public Response getAllEmployees() {
        return Response.ok(queryService.queryForAllEmployees()).build();
    }

    @GET
    @Path("/test/{i}")
    public String getTest(@PathParam("i") String s) {
        return s;
    }

    @GET
    @Path("/employees-in-project/{projectName}")
    public Response getEmployeesInProject(@PathParam("projectName") String projectName) {
        return Response.ok(queryService.queryForEmployeesInProject(projectName)).build();
    }

    @PUT
    @Path("/add-employee-to-project/{employeeId}/{projectId}")
    public String assignEmployeeToProject(@PathParam("employeId") Integer employeeId, @PathParam("projectId") Integer projectId){
        return queryService.addEmployeeToProject(employeeId, projectId);
    }

    @GET
    @Path("/get-employees-not-in-project/{roleName}")
    public Response getEmployeesNotInProject(@PathParam("roleName") String roleName){
        return Response.ok(queryService.queryForEmployeesWithRoleNotInProject(roleName)).build();
    }
}
