package io.sample.config.rest;

import io.sample.entity.TodoData;
import io.sample.service.TodoDataService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("todo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoRest {
    
    @Inject
    TodoDataService dataService;
    
    @Path("new")
    @POST
    public Response createTodoData(TodoData todoData) {
        dataService.createTodoData(todoData);
        return Response.ok(todoData).build();
    }
    
    @Path("update")
    @PUT
    public Response updateTodoData(TodoData todoData) {
        dataService.updateTodoData(todoData);
        return Response.ok(todoData).build();
    }
    
    @Path("{id}")
    @GET
    public TodoData getTodoDataById(@PathParam("id") Long id) {
        return dataService.findTodoDataById(id);
    }
    
    @Path("list")
    @GET
    public List<TodoData> getTodoDatas() {
        return dataService.getTodoDatas();
    }
    
    @Path("status")
    @POST
    public Response markCompleted(@QueryParam("id") Long id) {
        TodoData data = dataService.findTodoDataById(id);
        data.setCompleted(true);
        dataService.updateTodoData(data);
    
        return Response.ok(data).build();
    }
}
