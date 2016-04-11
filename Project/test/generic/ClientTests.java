package generic;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import server.Server;

public class ClientTests {

	
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		new Server().run(new String[] {"wipeout"});
		new Server().run(new String[] {"sqlite", "9000"});
		
		String[] testClasses = new String[] {
				"client.utils.TranslatorTest",
				
				
				"communication.CommandTests",
				"communication.HTTPProxyTest",
				"communication.MockProxyTests",
				"communication.PollerTests",
				"communication.FacadeTests",
				"communication.FacadeCanDoTestA",
				"communication.FacadeCanDoTestB",
				
				"gameMapTest.GameMapTest",
				"gameMapTest.HasMunicipalityTest",
				"gameMapTest.HasSettlementTest",
				"gameMapTest.LayCityTest",
				"gameMapTest.LaySettlementTest",
				"gameMapTest.VertexObjectFactoryTest",
				
				"turnTracker.TurnTrackerTest"
		};
		org.junit.runner.JUnitCore.main(testClasses);
	}
}
