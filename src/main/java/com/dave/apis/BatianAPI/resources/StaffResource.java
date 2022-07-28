package com.dave.apis.BatianAPI.resources;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
import com.dave.apis.BatianAPI.core.Staff;
import com.dave.apis.BatianAPI.service.StaffService;

import io.swagger.annotations.Api;

@Path("/staff")
@Produces(MediaType.APPLICATION_JSON)
@Api(value="Staff endpoint")
public class StaffResource {

	private final StaffService staffService;

	public StaffResource(StaffService staffService) {
		this.staffService = staffService;
	}

	@POST
	@Timed
	public Response createStaff(@NotNull @Valid final Staff staff) {
		Staff s = new Staff();
		s.setUserId(staff.getUserId());
		s.setFirstName(staff.getFirstName());
		s.setLastName(staff.getLastName());
		s.setPhone(staff.getPhone());
		s.setEmail(staff.getEmail());
		s.setPassword(staff.getPassword());
		s.setRegDate(staff.getRegDate());

		return Response.ok(staffService.createStaff(s)).build();
	}

	@GET
	@Timed
	@Path("{id}")
	public Response getStaff(@PathParam("id") final long id) {
		return Response.ok(staffService.getStaff(id)).build();
	}

	@GET
	@Timed
	public Response getStaffs() {
		return Response.ok(staffService.getStaffs()).build();
		}

	@PUT
	@Timed
	@Path("{id}")
	public Response editStaff(@NotNull @Valid final Staff staff , @PathParam("id") final long id) {
		staff.setUserId(id);

		return Response.ok(staffService.editStaff(staff)).build();

	}

	@DELETE
	@Timed
	@Path("{id}")
	public Response deleteStaff(@PathParam("id") final int id) {
		Map<String, String> response = new HashMap<>();
		response.put("status", staffService.deleteStaff(id));
		return Response.ok(response).build();
	}
}
