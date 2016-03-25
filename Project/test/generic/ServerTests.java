package generic;

public class ServerTests {
	public static void main(String[] args) {
		String[] testClasses = new String[] {
				"communication.moves.AcceptTradeCommandTest",
				"communication.moves.BuildCityCommandTest",
				"communication.moves.BuildRoadCommandTest",
				"communication.moves.BuildSettlementCommandTest",
				"communication.moves.BuyDevCardCommandTest",
				"communication.moves.DiscardCardsCommandTest",
				"communication.moves.FinishTurnCommandTest",
				"communication.moves.MaritimeTradeCommandTest",
				"communication.moves.MonopolyCommandTest",
				"communication.moves.MonumentCommandTest",
				"communication.moves.OfferTradeCommandTest",
				"communication.moves.RoadBuildingCommandTest",
				"communication.moves.RobPlayerCommandTest",
				"communication.moves.RollNumberCommandTest",
				"communication.moves.SendChatCommandTest",
				"communication.moves.SoldierCommandTest",
				"communication.moves.YearOfPlentyCommandTest",
		};
		org.junit.runner.JUnitCore.main(testClasses);
	}
}
