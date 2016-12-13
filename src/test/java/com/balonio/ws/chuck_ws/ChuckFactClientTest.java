package com.balonio.ws.chuck_ws;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ChuckFactClientTest {
	ChuckFactClient myClient;
	@Before
	public void setupTest(){
		myClient = new ChuckFactClient();
		myClient.init();
		
	}
@Test
	public void getRandomFactTest(){
		String response = myClient.getRandom();
		Assert.assertNotNull(response);
	}
}
