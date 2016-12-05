package com.balonio.ws.chuck_ws;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/fact")
public class ChuckResource {
	private final static Logger LOGGER = Logger.getLogger(ChuckResource.class.getName());

	
	@Inject
	ChuckFactClient chuckClient;
	@Path("/random")
	@GET
	public String getFact(){
		LOGGER.info("Sending request");
		String response = chuckClient.getRandom();
		if (response.isEmpty()){
			LOGGER.severe("got empty response");	
		}
		return response;
	}
}
