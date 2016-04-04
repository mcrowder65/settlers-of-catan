package server;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.sun.net.httpserver.HttpServer;

import server.facade.*;
import server.handlers.*;
import server.persistence.PersistenceProvider;
import server.plugin.ClassLoaderTool;
import server.plugin.ConfigurationTool;
import server.plugin.PluginDefinition;
import server.swagger.*;

public class Server {

	private final int MAX_WAITING_CONNECTIONS = 30;
	private HttpServer httpServer;
	public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
		new Server().run(args);
	}
	
	private AcceptTradeHandler acceptTradeHandler;
	private AddAIHandler addAIHandler;
	private BuildCityHandler buildCityHandler;
	private BuildRoadHandler buildRoadHandler;
	private BuildSettlementHandler buildSettlementHandler;
	private BuyDevCardHandler buyDevCardHandler;
	private CreateGameHandler createGameHandler;
	private DiscardCardsHandler discardCardsHandler;
	private FinishTurnHandler finishTurnHandler;
	private GetModelHandler getModelHandler;
	private JoinGameHandler joinGameHandler;
	private ListAIHandler listAIHandler;
	private ListGamesHandler listGamesHandler;
	private LoginHandler loginHandler;
	private MaritimeTradeHandler maritimeTradeHandler;
	private MonopolyHandler monopolyHandler;
	private MonumentHandler monumentHandler;
	private RegisterHandler registerHandler;
	private RoadBuildingHandler roadBuildingHandler;
	private RobPlayerHandler robPlayerHandler;
	private RollDiceHandler rollDiceHandler;
	private SendChatHandler sendChatHandler;
	private SoldierHandler soldierHandler;
	private YearOfPlentyHandler yearOfPlentyHandler;
	private OfferTradeHandler offerTradeHandler;
	public void run(String[] args) throws SAXException, IOException, ParserConfigurationException {
		
		if (args.length != 2) {
			System.out.println("Usage: ant our-server <persistence-type> <commands-between-checkpoints>");
			return;
		}
		String persistenceIdentifier = args[0];
		int commandsBetweenCheckpoints = Integer.parseInt(args[1]);
		
		
		List<PluginDefinition> definitions = ConfigurationTool.readConfigFile("plugin.xml");
		ClassLoaderTool.loadPlugins("resources");
	
	
		for (PluginDefinition def : definitions) {
			if (def.name.equals(persistenceIdentifier)) {
				Game.instance().initPersistanceProvider(commandsBetweenCheckpoints, def.path);
				break;
			}
		}
		if (Game.instance().getPersistenceProvider() == null) {
			System.out.println("Persistence identifier " + persistenceIdentifier + " was not found. Exiting.");
			return;
		}
		
	  
		
		
		int port = 8081;
		
		System.out.println("Port =" + port);
		
		
		try {
			httpServer = HttpServer.create(new InetSocketAddress(port), MAX_WAITING_CONNECTIONS);
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		
		IMovesFacade movesFacade = new ServerMovesFacade();
		IInnerGameFacade innerGameFacade = new ServerInnerGameFacade();
		IOuterGameFacade outerGameFacade = new ServerOuterGameFacade();
		IUserFacade  userFacade = new ServerUserFacade();
		
		loginHandler = new LoginHandler(userFacade);
		registerHandler = new RegisterHandler(userFacade);
		
		createGameHandler = new CreateGameHandler(outerGameFacade);
		listGamesHandler = new ListGamesHandler(outerGameFacade);
		joinGameHandler  = new JoinGameHandler(outerGameFacade);
		
		getModelHandler = new GetModelHandler(innerGameFacade);
		listAIHandler = new ListAIHandler(innerGameFacade);
		addAIHandler = new AddAIHandler(innerGameFacade);
		
		acceptTradeHandler = new AcceptTradeHandler(movesFacade);
		buildCityHandler = new BuildCityHandler(movesFacade);
		buildSettlementHandler = new BuildSettlementHandler(movesFacade);
		buildRoadHandler = new BuildRoadHandler(movesFacade);
		buyDevCardHandler = new BuyDevCardHandler(movesFacade);
		discardCardsHandler = new DiscardCardsHandler(movesFacade);
		finishTurnHandler = new FinishTurnHandler(movesFacade);
		maritimeTradeHandler = new MaritimeTradeHandler(movesFacade);
		monopolyHandler = new MonopolyHandler(movesFacade);
		monumentHandler = new MonumentHandler(movesFacade);
		roadBuildingHandler = new RoadBuildingHandler(movesFacade);
		robPlayerHandler = new RobPlayerHandler(movesFacade);
		rollDiceHandler = new RollDiceHandler(movesFacade);
		sendChatHandler = new SendChatHandler(movesFacade);
		soldierHandler = new SoldierHandler(movesFacade);
		yearOfPlentyHandler = new YearOfPlentyHandler(movesFacade);
		offerTradeHandler = new OfferTradeHandler(movesFacade);
		
		httpServer.setExecutor(null);
		
		httpServer.createContext("/docs/api/data", new JSONAppender("") );
		httpServer.createContext("/docs/api/view", new BasicFile("") );
		
		
		httpServer.createContext("/user/login", loginHandler);
		httpServer.createContext("/user/register", registerHandler);
		
		httpServer.createContext("/games/list", listGamesHandler);
		httpServer.createContext("/games/create", createGameHandler);
		httpServer.createContext("/games/join", joinGameHandler);
		
		httpServer.createContext("/game/model", getModelHandler);
		httpServer.createContext("/game/listAI", listAIHandler);
		httpServer.createContext("/game/addAI", addAIHandler);
		
		httpServer.createContext("/moves/offerTrade", offerTradeHandler);
		httpServer.createContext("/moves/acceptTrade", acceptTradeHandler);
		httpServer.createContext("/moves/buildCity", buildCityHandler);
		httpServer.createContext("/moves/buildSettlement", buildSettlementHandler);
		httpServer.createContext("/moves/buildRoad", buildRoadHandler);
		httpServer.createContext("/moves/buyDevCard", buyDevCardHandler);
		httpServer.createContext("/moves/discardCards", discardCardsHandler);
		httpServer.createContext("/moves/finishTurn", finishTurnHandler);
		httpServer.createContext("/moves/maritimeTrade", maritimeTradeHandler);
		httpServer.createContext("/moves/Monopoly", monopolyHandler);
		httpServer.createContext("/moves/Monument", monumentHandler);
		httpServer.createContext("/moves/Road_Building", roadBuildingHandler);
		httpServer.createContext("/moves/robPlayer", robPlayerHandler);
		httpServer.createContext("/moves/rollNumber", rollDiceHandler);
		httpServer.createContext("/moves/sendChat", sendChatHandler);
		httpServer.createContext("/moves/Soldier", soldierHandler);
		httpServer.createContext("/moves/Year_Of_Plenty", yearOfPlentyHandler);
		

		
		
		httpServer.start();
		
		
	}
}
