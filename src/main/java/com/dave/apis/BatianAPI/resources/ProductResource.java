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
import com.dave.apis.BatianAPI.core.Product;
import com.dave.apis.BatianAPI.service.ProductService;

import io.swagger.annotations.Api;

@Path("/product")
@Produces(MediaType.APPLICATION_JSON)
@Api(value="Product endpoint")
public class ProductResource {

	private final ProductService productService;

	public ProductResource(ProductService productService) {
		this.productService = productService;
	}

	@POST
	@Timed
	public Response createProduct(@NotNull @Valid final Product product) {
		Product p = new Product();
		p.setProductId(product.getProductId());
		p.setName(product.getName());
		p.setDescription(product.getDescription());
		p.setPrice(product.getPrice());
		p.setCategoryId(product.getCategoryId());
		p.setRegDate(product.getRegDate());

		return Response.ok(productService.createProduct(p)).build();
	}

	@GET
	@Timed
	@Path("{id}")
	public Response getProduct(@PathParam("id") final int id) {
		return Response.ok(productService.getProduct(id)).build();
	}

	@GET
	@Timed
	public Response getProducts() {
		return Response.ok(productService.getProducts()).build();
		}

	@PUT
	@Timed
	@Path("{id}")
	public Response editProduct(@NotNull @Valid final Product product , @PathParam("id") final int id) {
		product.setProductId(id);

		return Response.ok(productService.editProduct(product)).build();

	}

	@DELETE
	@Timed
	@Path("{id}")
	public Response deleteProduct(@PathParam("id") final int id) {
		Map<String, String> response = new HashMap<>();
		response.put("status", productService.deleteProduct(id));
		return Response.ok(response).build();
	}
}
