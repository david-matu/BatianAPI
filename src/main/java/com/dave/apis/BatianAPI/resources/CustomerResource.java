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
import com.dave.apis.BatianAPI.core.Customer;
import com.dave.apis.BatianAPI.service.CustomerService;

import io.swagger.annotations.Api;

@Path("/customer")
@Produces(MediaType.APPLICATION_JSON)
@Api(value="Customer endpoint")
public class CustomerResource {

	private final CustomerService customerService;

	public CustomerResource(CustomerService customerService) {
		this.customerService = customerService;
	}

	@POST
	@Timed
	public Response createCustomer(@NotNull @Valid final Customer customer) {
		Customer c = new Customer();
		c.setUserId(customer.getUserId());
		c.setFirstName(customer.getFirstName());
		c.setLastName(customer.getLastName());
		c.setPhone(customer.getPhone());
		c.setEmail(customer.getEmail());
		c.setPassword(customer.getPassword());
		c.setRegDate(customer.getRegDate());

		return Response.ok(customerService.createCustomer(c)).build();
	}

	@GET
	@Timed
	@Path("{id}")
	public Response getCustomer(@PathParam("id") final long id) {
		return Response.ok(customerService.getCustomer(id)).build();
	}

	@GET
	@Timed
	public Response getCustomers() {
		return Response.ok(customerService.getCustomers()).build();
		}

	@PUT
	@Timed
	@Path("{id}")
	public Response editCustomer(@NotNull @Valid final Customer customer , @PathParam("id") final long id) {
		customer.setUserId(id);

		return Response.ok(customerService.editCustomer(customer)).build();

	}

	@DELETE
	@Timed
	@Path("{id}")
	public Response deleteCustomer(@PathParam("id") final int id) {
		Map<String, String> response = new HashMap<>();
		response.put("status", customerService.deleteCustomer(id));
		return Response.ok(response).build();
	}
}
