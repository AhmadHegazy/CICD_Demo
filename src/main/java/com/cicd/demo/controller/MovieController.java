package com.cicd.demo.controller;

import java.net.URI;
import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cicd.demo.model.Movie;
import com.cicd.demo.service.MovieService;


@Component
@Path("/movies")
public class MovieController {

	@Autowired
	MovieService movieService;
	
	// API End-point to get a list of all movies in the database
	@GET
	@Produces("application/json")
	public Collection<Movie> movies() {
		return movieService.getAllMovies();
	}
	
	// API End-point to get a specific movie by id
	@GET
	@Path("/{id}")
	@Produces("application/json")
	public Movie getMovie(@PathParam("id") String id) {
		return movieService.getMovie(id);
	}
	
	// API End-point to add a new movie to database
	@POST
	@Consumes("application/json")
	public Response add(Movie movie, @Context UriInfo info) {
		movieService.addMovie(movie);
		return Response.created(URI.create(
				info.getAbsolutePath().toString()+"/"+movie.getId()
				)).build();
	}
	
	//API End-point to modify a movie
		@PUT
		@Path("/{id}")
		@Consumes("application/json")
		@Produces("application/json")
		public Movie update(@PathParam("id") String id, Movie movie) {
			movieService.updateMovie(id, movie);
			return movie;
		}
		// API End-point to delete a movie
		@DELETE
		@Path("/{id}")
		public Response delete(@PathParam("id") String id) {
			movieService.removeMovie(id);
			return Response.ok(id).build();
		}
}
