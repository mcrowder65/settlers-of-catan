package communication;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import client.communication.*;
import shared.communication.response.LoginResponse;
import shared.communication.response.RegisterResponse;

public class HTTPProxyTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLogin(){
		HTTPProxy httpProxy = new HTTPProxy(2, 
				"localhost", 8081);
		LoginResponse response = httpProxy.login("matt", "crowder");
		System.out.println("login test");
	}
//	@Test
//	public void testRegister(){
//		HTTPProxy httpProxy = new HTTPProxy(2, 
//				"localhost", 8081);
//		
//		RegisterResponse response = httpProxy.register("matt", "crowder");
//		System.out.println("register test");
//	}
	
}
