package generic;

public class ClientTests {

	
	public static void main(String[] args) {
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
