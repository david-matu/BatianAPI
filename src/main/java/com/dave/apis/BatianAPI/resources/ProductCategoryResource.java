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
import com.dave.apis.BatianAPI.core.ProductCategory;
import com.dave.apis.BatianAPI.service.ProductCategoryService;

import io.swagger.annotations.Api;

@Path("/productcategory")
@Produces(MediaType.APPLICATION_JSON)
@Api(value="ProductCategory endpoint")
public class ProductCategoryResource {

	private final ProductCategoryService productcategoryService;

	public ProductCategoryResource(ProductCategoryService productcategoryService) {
		this.productcategoryService = productcategoryService;
	}

	@POST
	@Timed
	public Response createProductCategory(@NotNull @Valid final ProductCategory productcategory) {
		ProductCategory p = new ProductCategory();
		p.setCategoryId(productcategory.getCategoryId());
		p.setCategory(productcategory.getCategory());
		p.setDescription(productcategory.getDescription());

		return Response.ok(productcategoryService.createProductCategory(p)).build();
	}

	@GET
	@Timed
	@Path("{id}")
	public Response getProductCategory(@PathParam("id") final int id) {
		return Response.ok(productcategoryService.getProductCategory(id)).build();
	}

	@GET
	@Timed
	public Response getProductCategorys() {
		return Response.ok(productcategoryService.getProductCategorys()).build();
		}

	@PUT
	@Timed
	@Path("{id}")
	public Response editProductCategory(@NotNull @Valid final ProductCategory productcategory , @PathParam("id") final int id) {
		productcategory.setCategoryId(id);

		return Response.ok(productcategoryService.editProductCategory(productcategory)).build();

	}

	@DELETE
	@Timed
	@Path("{id}")
	public Response deleteProductCategory(@PathParam("id") final int id) {
		Map<String, String> response = new HashMap<>();
		response.put("status", productcategoryService.deleteProductCategory(id));
		return Response.ok(response).build();
	}
}
