package org.acme.openapi;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

  @Path("/task")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public class TaskResource {

    Set<Task> tasks = Collections.newSetFromMap(
        Collections.synchronizedMap(new LinkedHashMap<>()));

    public TaskResource() {
      tasks.add(new Task("First task", 
            LocalDateTime.now().plusDays(3), false));
      tasks.add(new Task("Second task", 
            LocalDateTime.now().plusDays(6), false));
    }

    @GET
    @Operation(summary = "Get all tasks", 
               description = "Get the full list of tasks.")
    public Set<Task> list() {
      return tasks;
    }

    @POST
    @Operation(summary = "Create a new task")
    public Set<Task> add(
        @Parameter(required = true, content = 
          @Content(schema = @Schema(implementation = Task.class))) Task task) {
      tasks.add(task);
      return tasks;
    }

    @DELETE
    @Operation(summary = "Remove the specified task")
    public Set<Task> delete(
        @Parameter(required = true, 
        content = @Content(schema = @Schema(implementation = Task.class)))
        Task task) {
      tasks.removeIf(existingTask -> existingTask.equals(task));
      return tasks;
    }
  }
