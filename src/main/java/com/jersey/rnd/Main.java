package com.jersey.rnd;

import java.io.IOException;
import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.jersey.rnd.inject.ApplicationBinder;

public class Main {

	public static final String BASE_URI = "http://localhost:8081/api";

	public static HttpServer startServer() {

		final ResourceConfig resourceConfig = new ResourceConfig().packages("com.jersey.rnd.resource");
		resourceConfig.register(new ApplicationBinder());
		return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), resourceConfig);
	}

	public static void main(String[] args) throws IOException {
		final HttpServer server = startServer();
		System.out.println(String.format(
				"Jersey app started with WADL available at " + "%sapplication.wadl\nHit enter to stop it...",
				BASE_URI));
		System.in.read();
		server.stop();
	}

}
