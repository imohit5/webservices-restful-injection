package com.injection.service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;

import com.injection.persistence.Passenger;
import com.injection.persistence.PassengerList;

@Path("/Passenger")
@Produces("application/xml")
public class PassengerService {

	PassengerList passengersList = new PassengerList();

	public PassengerService() {
		init();
	}

	public void init() {
		ArrayList<Passenger> passengers = new ArrayList<Passenger>();
		Passenger passenger = new Passenger();
		passenger.setId(12);
		passenger.setName("Tim");
		passengers.add(passenger);
		passengersList.setPassengers(passengers);
	}

	@GET
	public PassengerList getPassengers(@QueryParam("start") int start, @QueryParam("size") int size,
			@HeaderParam("airline") String airline, @Context HttpHeaders headers) {
		// @CookieParam can also be used
		Map<String, Cookie> cookies = headers.getCookies();
		Set<String> keySetCookies = cookies.keySet();
		for (String cookieKey : keySetCookies) {
			Cookie cookie = cookies.get(cookieKey);
			System.out.println("cookies: " + cookie.getValue());
		}

		MultivaluedMap<String, String> requestHeaders = headers.getRequestHeaders();
		Set<String> keySet = requestHeaders.keySet();

		for (String key : keySet) {
			System.out.println("requestHeaders: " + requestHeaders.get(key));
		}

		for (String key : keySet) {
			System.out.println("headers: " + headers.getHeaderString(key));
		}

		System.out.println(start);
		System.out.println(size);
		System.out.println(airline);
		return passengersList;
	}

	@POST
	public void addPassenger(@FormParam("firstname") String firstName, @FormParam("lastname") String lastName) {
		System.out.println(firstName);
		System.out.println(lastName);
	}

}
