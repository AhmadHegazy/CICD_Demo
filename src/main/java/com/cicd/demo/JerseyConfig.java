package com.cicd.demo;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.cicd.demo.controller.MovieController;



@Component
public class JerseyConfig extends ResourceConfig{

	public JerseyConfig() {
		register(MovieController.class);
	}
}
