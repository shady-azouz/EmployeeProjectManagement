package Controllers;

import DataTransferObjects.JsonPaginatorDTO;
import Entities.Employee;
import Services.QueryService;
import Services.QueryServiceImpl;

import javax.json.Json;
import javax.json.bind.annotation.JsonbTransient;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/controller")
public class ApplicationController {
    QueryService queryService = new QueryServiceImpl();

    @GET
    @Path("/employees")
    @Produces(MediaType.APPLICATION_JSON)
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
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesInProject(@PathParam("projectName") String projectName) {
        return Response.ok(queryService.queryForEmployeesInProject(projectName)).build();
    }

    @GET 
    @Path("/add-employee-to-project/{employeeId}/{projectId}")
    public Response assignEmployeeToProject(@PathParam("employeeId") Integer employeeId, @PathParam("projectId") Integer projectId){
        return Response.ok(queryService.addEmployeeToProject(employeeId, projectId)).build();
    }

    @GET
    @Path("/get-employees-not-in-project/{roleName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesNotInProject(@PathParam("roleName") String roleName){
        return Response.ok(queryService.queryForEmployeesWithRoleNotInProject(roleName)).build();
    }

    @GET
    @Path("/get-paginated-employees/{pageIndex}/{pageSize}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesPaginated(@PathParam("pageIndex") int pageIndex, @PathParam("pageSize") int pageSize){
        return Response.ok(queryService.queryForPaginatedEmployees(pageIndex,pageSize)).build();
    }

    @POST
    @Path("/get-paginated-employees-with-json")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeesPaginatedWithJson(JsonPaginatorDTO jsonPaginatorDTO){
        return Response.ok(queryService.queryForPaginatedEmployees(jsonPaginatorDTO.getPageIndex(),jsonPaginatorDTO.getPageSize())).build();
    }
}
