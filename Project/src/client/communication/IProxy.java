package client.communication;

import java.util.List;

import shared.communication.response.*;
import shared.definitions.*;
import shared.locations.*;

/**
 * Classes that implement this interface act as a proxy for 
 * the GameManager to interact to the server through.
 * @author Eric
 *
 */
public interface IProxy {

	/**
	 * Logs in to the server.
	 * @param username
	 * @param password
	 * @return
	 * Returns the server's response.
	 */
	public LoginResponse login(String username, String password)throws IllegalArgumentException ;
	/**
	 * Registers a new player.
	 * @param username
	 * @param password
	 * @return
	 * Returns the server's response.
	 */
	public RegisterResponse register(String username, String password)throws IllegalArgumentException ;
	/**
	 * Lists the current games
	 * @return
	 *  Returns the server's response, with the list of games
	 *  if successful
	 */
	public ListGamesResponse listGames();

	/**
	 * Creates a new game.
	 * @param name
	 * The name of the game.
	 * @return
	 * Returns the server's response, with the game's properties
	 * if successful
	 */
	public CreateGameResponse createGame(String name)throws IllegalArgumentException ;
	/**
	 * Joins a current game.
	 * @param gameId
	 * The ID of the game.
	 * @param playerColor
	 * The color the player will be.
	 * @return
	 *  Returns the server's response.
	 */
	public JoinGameResponse joinGame(int id, CatanColor color)throws IllegalArgumentException ;

	/**
	 * Loads a game on the server. Used for testing purposes.
	 * @param name
	 * The name of the saved game file.
	 * @return
	 * Returns the server response.
	 */
	public Response loadGame(String name)throws IllegalArgumentException ;
	
/**
 *  Saves a game on the server's file system. Used for testing purposes.
 * @param fileName
 * The name of the file to save as.
 * @return
 * Returns the server's response.
 */
	public Response saveGame(String fileName)throws IllegalArgumentException ;
	/**
	 * Resets the command history of the current game.
	 * @return
	 * Returns the server response.
	 */
	public GetModelResponse reset();
	/**
	 * Gets the current Game Model.
	 * @return
	 * Returns the server response, including the Game Model
	 * if successful.
	 */
	public GetModelResponse getModel();
	/**
	 * Gets the current Game Model.
	 * @param version
	 * Includes the local version number in the request.
	 * @return
	 * Returns the server response, including the Game Model
	 * if successful, or "true" if no new version available.
	 */
	public GetModelResponse getModel(int version);
	/**
	 * Returns a list of supported AI player types.
	 * (Only supported type = LARGEST_ARMY)
	 * @return
	 * Returns the server response, including an enumeration of the 
	 * AI types if successful.
	 */
	public ListAIResponse listAI();
	/**
	 * Adds an AI to the game
	 * @param aiType
	 * The type of AI, which is a type returned by listAI()
	 * @return
	 * Returns the server response
	 */
	public Response addAI(String aiType)throws IllegalArgumentException ;
	/**
	 * Gets a list of commands that have been executed
	 * in the current game.
	 * @return
	 * Returns the servers response, including the list of
	 * commands if successful
	 */
	public GetCommandsResponse getCommands();
	
	/**
	 * Executes the list of commands.
	 * @param commands
	 * @return 
	 * Returns the server response, including the final game model,
	 * if successful
	 * @throws IllegalArgumentException
	 * commands cannot be null.
	 */
	public GetModelResponse executeCommands(List<String> commands) throws IllegalArgumentException;
	/**
	 * Sends a chat message to other players.
	 * @param content
	 * The content of the message.
	 * @return
	 * Returns the server's response.
	 */
	public Response sendChat(String content);
	/**
	 * Sends the player's result from rolling dice to the server.
	 * @param number
	 * The combined number rolled.
	 * @return
	 * Returns the server's response
	 */
	public Response rollNumber(int number)throws IllegalArgumentException ;
	/**
	 * Robs a player of a 
	 * @param victimIndex
	 * The index of the player you're robbing (-1 if none)
	 * @param location
	 * The new robber location
	 * @return 
	 * Returns the server's response
	 */
	public Response robPlayer(int victimIndex, HexLocation location)throws IllegalArgumentException ;
	/**
	 * Finishes the player's turn.
	 * @return
	 * Returns the server's response
	 */
	public Response finishTurn();
	/**
	 * Purchases a new Development Card
	 * @return
	 * Returns the server's response
	 */
	public Response buyDevCard();
	/**
	 * Plays the Year of Plenty card.
	 * @param resource1
	 * The first resource to gain.
	 * @param resource2
	 * The second resource to gain.
	 * @return
	 * Returns the server's response
	 */
	public Response Year_Of_Plenty(ResourceType resource1, ResourceType resource2)throws IllegalArgumentException ;
	/**
	 * Plays the Road Building card.
	 * @param spot1
	 * The first location to build a road.
	 * @param spot2
	 * The second location to build a road.
	 * @return
	 * Returns the server's response.
	 */
	public Response Road_Building(EdgeLocation spot1, EdgeLocation spot2)throws IllegalArgumentException ;
	/**
	 * Plays the soldier card.
	 * @param victimIndex
	 * The index of the player to rob.
	 * @param location
	 * The new location of the robber.
	 * @return
	 * Returns the server's response
	 */
	public Response Soldier(int victimIndex, HexLocation location)throws IllegalArgumentException ;
	/**
	 * Plays the Monopoly card.
	 * @param resource
	 * The declared resource.
	 * @return
	 * Returns the server's response
	 */
	public Response Monopoly(ResourceType resource);
	/**
	 * Plays the Monument card.
	 * @return
	 * Returns the server's response
	 */
	public Response Monument();
	/**
	 * Builds a road.
	 * @param roadLocation
	 * The location of the new road.
	 * @param free
	 * Whether the road is built for 'free' (i.e. at the start of the game)
	 * @return
	 * Returns the server's response
	 */
	public Response buildRoad(EdgeLocation roadLocation, boolean free)throws IllegalArgumentException ;
	/**
	 * Builds a settlement.
	 * @param vertexLocation
	 * The location of the new settlement.
	 * @param free
	 * Whether the settlement is built for 'free' (i.e. at the start of the game)
	 * @return
	 * Returns the server's response
	 */
	public Response buildSettlement(VertexLocation vertexLocation, boolean free)throws IllegalArgumentException ;
	/**
	 * Builds a city.
	 * @param vertexLocation
	 * The location of the new city.
	 * @return
	 * Returns the server's response
	 */
	public Response buildCity(VertexLocation vertexLocation)throws IllegalArgumentException ;
	/**
	 * Offers a trade to a player
	 * @param offer
	 * The trade offer, containing the resources and target player.
	 * @return
	 * Returns the server's response.
	 */
	public Response offerTrade(TradeOffer offer)throws IllegalArgumentException ;
	/**
	 * Offers a trade to the bank.
	 * @param ratio
	 * The ratio between the input resource and output resource.
	 * e.g. 2 grain for 1 sheep would be ratio = 2
	 * @param inputResource
	 * The resource to pay.
	 * @param outputResource
	 * The resource to receive.
	 * @return
	 * Returns the server's response.
	 */
	public Response maritimeTrade(int ratio, ResourceType inputResource, ResourceType outputResource)throws IllegalArgumentException ;
	/**
	 * Accepts or declines a trade offer.
	 * @param willAccept
	 * True if accept, false if reject.
	 * @return
	 * Returns the server's response
	 */
	public Response acceptTrade(boolean willAccept);
	/**
	 * Discards a number or resources.
	 * @param discardedCards
	 * The resources to discard.
	 * @return
	 * Returns the server's response
	 */
	public Response discardCards(ResourceList discardedCards)throws IllegalArgumentException ;
	
	/**
	 * Sets the server's logging level.
	 * @param loggingLevel
	 * The logging level.
	 * @return
	 * Returns the server's response.
	 */
	public Response changeLogLevel(String loggingLevel)throws IllegalArgumentException ;
	
}
