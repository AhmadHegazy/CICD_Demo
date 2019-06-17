package com.cicd.demo.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Service;

import com.cicd.demo.model.Genre;
import com.cicd.demo.model.Movie;


@Service
public class MovieService {

	private final ConcurrentMap<String, Movie> db;
	
	private static final String[] data = {
            "1", "Raging Bull", "1980", "DRAMA",
            "2", "The Green Mile", "1999", "CRIME",
            "3", "The God Father", "1972", "DRAMA",
            "4", "The God Father part 2", "1994", "DRAMA",
            "5", "The God Father part 3", "1990", "DRAMA",
            // String id, String name, String year, String genre
    } ;
	
	public MovieService() {
		this.db = new ConcurrentHashMap<>();
		this.initializeDatabase();
	}

	// Adds a few movies to the database.
	protected void initializeDatabase() {
		Iterator<String> l = Arrays.asList(data).iterator();
		if(this.db.isEmpty()) {
			while (l.hasNext()) {
				Movie movie = new Movie();
				movie.setId(l.next());
				movie.setName(l.next());
				movie.setYear(l.next());
				movie.setGenre(Genre.valueOf(l.next()));
				this.db.put(movie.getId(), movie);
			}
		}
	}
	
	// Get all the movies stored in the database
	public Collection<Movie> getAllMovies() {
		Collection<Movie> all = this.db.values();
		if (all.isEmpty()) {
			return Collections.emptyList();
		} else {
			return all;
		}
	}
	// Add a movie to database
	public void addMovie(Movie f) {
		if(f.getId() == null) {
			f.setId(String.valueOf(this.db.size()+1));
		}
		this.db.put(f.getId(), f);
	}
	// Get a movie by id
	public Movie getMovie(String id) {
		return this.db.get(id);
	}
	// Modify a movie attributes
	public Movie updateMovie(String id, Movie f) {
		if(!this.db.containsKey(id)) {
			throw new IllegalArgumentException("Invalid Movie or Movie does not exist!");
		}
		if((f.getId() == null) || (id != f.getId())) {
			f.setId(id);
		}
		return this.db.put(f.getId(), f);
	}
//	commented for third git commit
	// Delete a movie from database
//	public void removeMovie(String id) {
//		if(!this.db.containsKey(id)) {
//			throw new IllegalArgumentException("Invalid Movie or Movie does not exist!");
//		}
//		this.db.remove(id);
//	}
	
}
