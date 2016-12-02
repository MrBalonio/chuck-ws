package com.balonio.ws.chuck_ws;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/fact")
public class ChuckResource {
	@Inject
	ChuckFactClient chuckClient;
	@Path("/random")
	@GET
	public String getFact(){
		String response = chuckClient.getRandom();
		
		return response;
	}
}
