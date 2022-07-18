package com.dave.apis.batian.resources;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.codahale.metrics.annotation.Timed;
import com.dave.apis.batian.core.Employee;
import com.dave.apis.batian.service.EmployeeService;

import io.swagger.annotations.Api;

@Path("employee")
@Produces(MediaType.APPLICATION_JSON)
@Api(value="This is Employee Resource for perrforming CRUD operations on Employee Table")
public class EmployeeResource {
	/*
	 *@Produces(MediaType.APPLICATION_JSON)
	 *@Consumes(MediaType.APPLICATION_JSON)
	 * Why no two @Consumes and @Produces
	 * [[FATAL] A resource model has ambiguous (sub-)resource method for HTTP method GET and input mime-types 
	 * 	as defined by"@Consumes" and "@Produces" annotations at Java methods 
	 * 	public javax.ws.rs.core.Response io.swagger.jaxrs.listing.ApiListingResource.getListing(javax.ws.rs.core.Application,javax.servlet.ServletConfig,javax.ws.rs.core.HttpHeaders,javax.ws.rs.core.UriInfo,java.lang.String) and public javax.ws.rs.core.Response io.swagger.jaxrs.listing.ApiListingResource.getListing(javax.ws.rs.core.Application,javax.servlet.ServletConfig,javax.ws.rs.core.HttpHeaders,javax.ws.rs.core.UriInfo,java.lang.String) at matching regular expression /swagger\.(json|yaml). These two methods produces and consumes exactly the same mime-types and therefore their invocation as a resource methods will always fail.; source='org.glassfish.jersey.server.model.RuntimeResource@625a4ff']
	 */
	private final EmployeeService empService;

	public EmployeeResource(EmployeeService es) {
		this.empService = es;
	}
	
	@GET
	@Timed
	public Response getEmployees() {
		return Response.ok(empService.getEmployees()).build();
	}
	
	@GET
	@Timed
	@Path("{id}")
	public Response getEmployee(@PathParam("id") final int id) {
		return Response.ok(empService.getEmployee(id)).build();
	}
	
	@POST
	@Timed
	public Response createEmployee(@NotNull @Valid final Employee employee) {
		Employee e = new Employee();
		e.setName(employee.getName());
		e.setDepartment(employee.getDepartment());
		e.setSalary(employee.getSalary());
		
		return Response.ok(empService.createEmployee(e)).build();
	}
	
	
	@PUT
	@Timed
	@Path("{id}")
	public Response editEmployee(@NotNull @Valid final Employee employee, @PathParam("id") final int id) {
		employee.setId(id);
		
		return Response.ok(empService.editEmployee(employee)).build();
	}
	
	@DELETE
	@Timed
	@Path("{id}")
	public Response deleteEmployee(@PathParam("id") final int id) {
		Map<String, String> response = new HashMap<>();
		response.put("status", empService.deleteEmployee(id));
		return Response.ok(response).build();
	}
}	
