package eu.mosov.steamTradeHelper.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("hello") @Produces("text/json")
public class HelloResource {

	@GET
	public String hello() {
		return "Hello";
	}
}
