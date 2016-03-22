package generic;

public class ServerTests {
	public static void main(String[] args) {
		String[] testClasses = new String[] {
				"communication.CommandExecutionTests",
		};
		org.junit.runner.JUnitCore.main(testClasses);
	}
}
