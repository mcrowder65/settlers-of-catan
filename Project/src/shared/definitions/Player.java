package shared.definitions;

import shared.locations.*;
/**
 * class that holds all the attributes of a player
 * @author Brennen
 *
 */
public class Player {

	/**
	 * How many cities this player has left to play,
	 */
	private int cities;
	/**
	 * Color of player 
	 */
	private CatanColor color;
	/**
	 * Whether this player has discarded or not already this discard phase.,
	 */
	private Boolean discarded;
	/**
	 * How many monuments this player has played
	 */
	private int monuments;
	/**
	 * Name of player
	 */
	private String name;
	/**
	 * The dev cards the player bought this turn.,
	 */
	private DevCardList newDevCards;
	/**
	 * The dev cards the player had when the turn started.,
	 */
	private DevCardList oldDevCards;
	/**
	 * What place in the array is this player? 0-3. It determines their turn order. 
	 */
	private int playerIndex;
	/**
	 *  Whether the player has played a dev card this turn.,
	 */
	private Boolean playedDevCard;
	/**
	 * The unique playerID. This is used to pick the client player apart from the
		others. This is only used here and in your cookie.,
	 */
	private int playerID;
	/**
	 * The resource cards this player has.,
	 */
	private ResourceList resources;
	/**
	 * Number of roads player has left to play
	 */
	private int roads;
	/**
	 * number of settlements the player has left to play
	 */
	private int settlements;
	/**
	 * number of soldier cards the player has
	 */
	private int soldiers;
	/**
	 * number of victoryPoints the player has
	 */
	private int victoryPoints;
	
	/**
	 * Player Constructor
	 * @param name
	 * @param color
	 * @param playerID
	 * @param playerIndex
	 * @throws IllegalArgumentException
	 */
	public Player(String name, CatanColor color, int playerID, int playerIndex) throws IllegalArgumentException {
		
	
	}
	
	/**
	 * Player Constructor
	 * sets all player attributes
	 * @param cities
	 * @param color
	 * @param discarded
	 * @param monuments
	 * @param name
	 * @param newDevCards
	 * @param oldDevCards
	 * @param playerIndex
	 * @param playedDevCard
	 * @param playerID
	 * @param resources
	 * @param roads
	 * @param settlements
	 * @param soldiers
	 * @param victoryPoints
	 */
	public Player(int cities, CatanColor color, Boolean discarded, int monuments,
			String name, DevCardList newDevCards, DevCardList oldDevCards,
			int playerIndex, Boolean playedDevCard, int playerID,
			ResourceList resources, int roads, int settlements, int soldiers,
			int victoryPoints) throws IllegalArgumentException  {

		this.cities = cities;
		this.color = color;
		this.discarded = discarded;
		this.monuments = monuments;
		this.name = name;
		this.newDevCards = newDevCards;
		this.oldDevCards = oldDevCards;
		this.playerIndex = playerIndex;
		this.playedDevCard = playedDevCard;
		this.playerID = playerID;
		this.resources = resources;
		this.roads = roads;
		this.settlements = settlements;
		this.soldiers = soldiers;
		this.victoryPoints = victoryPoints;
	}

	/**
	 * retrieves number of cities 
	 * @return cities
	 */
	public int getCities() {
		return cities;
	}

	/**
	 * retrieves color of the player
	 * @return color
	 */
	public CatanColor getColor() {
		return color;
	}

	/**
	 * retrieves discarded player attribute
	 * @return discarded
	 */
	public Boolean getDiscarded() {
		return discarded;
	}

	/**
	 * retrieves number of monuments 
	 * @return monuments
	 */
	public int getMonuments() {
		return monuments;
	}

	/**
	 * retrieves name of player
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * retrieves list of Dev cards that cannot be played until the player's next turn
	 * @return newDevCards
	 */
	public DevCardList getNewDevCards() {
		return newDevCards;
	}

	/**
	 * retrieves list of dev cards that are currently playable
	 * @return oldDevCards
	 */
	public DevCardList getOldDevCards() {
		return oldDevCards;
	}

	/**
	 * retrieves the playerIndex
	 * @return playerIndex
	 */
	public int getPlayerIndex() {
		return playerIndex;
	}

	/**
	 * retrieves wether or not the player used any dev cards
	 * @return playerDevCard
	 */
	public Boolean getPlayedDevCard() {
		return playedDevCard;
	}

	/**
	 * retrieves the id of the player
	 * @return playerID
	 */
	public int getPlayerID() {
		return playerID;
	}

	/**
	 * retrieves list of resources that the player has
	 * @return resources
	 */
	public ResourceList getResources() {
		return resources;
	}

	/**
	 * retrieves number of roads the player has
	 * @return roads
	 */
	public int getRoads() {
		return roads;
	}

	/**
	 * retrieves number of settlements
	 * @return settlements
	 */
	public int getSettlements() {
		return settlements;
	}

	/**
	 * retrieves number of soldiers
	 * @return soldiers
	 */
	public int getSoldiers() {
		return soldiers;
	}

	/**
	 * retrieves number of victory points the player has
	 * @return victoryPoints
	 */
	public int getVictoryPoints() {
		return victoryPoints;
	}

	/**
	 * Sets number of cities
	 * @param cities
	 */
	public void setCities(int cities) throws IllegalArgumentException  {
		this.cities = cities;
	}

	/**
	 * sets the color of the player
	 * @param color
	 */
	public void setColor(CatanColor color)   {
		this.color = color;
	}

	/**
	 * sets the discarded variable
	 * @param discarded
	 */
	public void setDiscarded(Boolean discarded) throws IllegalArgumentException  {
		this.discarded = discarded;
	}

	/**
	 * sets number of monuments player has played
	 * @param monuments
	 */
	public void setMonuments(int monuments) throws IllegalArgumentException  {
		this.monuments = monuments;
	}

	/**
	 * sets the name of the player
	 * @param name
	 */
	public void setName(String name) throws IllegalArgumentException  {
		this.name = name;
	}

	/**
	 * sets the newDevCardList
	 * @param newDevCards
	 */
	public void setNewDevCards(DevCardList newDevCards) throws IllegalArgumentException  {
		this.newDevCards = newDevCards;
	}

	/**
	 * sets the oldDevCardList
	 * @param oldDevCards
	 */
	public void setOldDevCards(DevCardList oldDevCards) throws IllegalArgumentException  {
		this.oldDevCards = oldDevCards;
	}

	/**
	 * sets the player index
	 * @param playerIndex
	 */
	public void setPlayerIndex(int playerIndex) throws IllegalArgumentException  {
		this.playerIndex = playerIndex;
	}

	/**
	 * sets if the player has played the devCard
	 * @param playedDevCard
	 */
	public void setPlayedDevCard(Boolean playedDevCard) throws IllegalArgumentException  {
		this.playedDevCard = playedDevCard;
	}

	/**
	 * sets playerID
	 * @param playerID
	 */
	public void setPlayerID(int playerID) throws IllegalArgumentException  {
		this.playerID = playerID;
	}

	/**
	 * sets ResourceList 
	 * @param resources
	 */
	public void setResources(ResourceList resources) throws IllegalArgumentException  {
		this.resources = resources;
	}

	/**
	 * sets number of roads the player has left to play
	 * @param roads
	 */
	public void setRoads(int roads) throws IllegalArgumentException  {
		this.roads = roads;
	}

	/**
	 * Sets number of settlements the player has left to play
	 * @param settlements
	 */
	public void setSettlements(int settlements) throws IllegalArgumentException  {
		this.settlements = settlements;
	}

	/**
	 * Sets number of soldiers the player has 
	 * @param soldiers
	 */
	public void setSoldiers(int soldiers)throws IllegalArgumentException  {
		this.soldiers = soldiers;
	}

	/**
	 * sets the number of victoryPoints the player has
	 * @param victoryPoints
	 */
	public void setVictoryPoints(int victoryPoints) throws IllegalArgumentException  {
		this.victoryPoints = victoryPoints;
	}
	
	public boolean canBuildRoad(){
		int brick = resources.getBrick();
		int wood = resources.getWood();
		if(brick >0 && wood>0){
			return true;
		}
		int roadBuildingCard = oldDevCards.getRoadBuilding();
		if(roadBuildingCard > 0){
			return true;
		}
		
		return false;
	}
	
	public boolean canBuildSettlement(){
		int brick = resources.getBrick();
		int wood = resources.getWood();
		int sheep = resources.getSheep();
		int grain = resources.getWheat();
		
		if(brick>0 && wood>0 && sheep>0 && grain>0){
			return true;
		}
		return false;
	}
	
	public boolean canBuildCity(){
		int ore = resources.getOre();
		int grain = resources.getWheat();
		
		if(ore>0 && grain>0){
			return true;
		}
		return false;
	}
	
	public boolean canBuyDevCard(){
		int ore = resources.getOre();
		int sheep = resources.getSheep();
		int grain = resources.getWheat();
		if(ore>0 && sheep>0 && grain >0){
			return true;
		}
		
		return false;
	}
	
	public boolean canPlayRoadBuilding(){
		int roadBuildingCard = oldDevCards.getRoadBuilding();
		if(roadBuildingCard > 0){
			return true;
		}
		return false;
	}
	
	public boolean canPlayYearOfPlentyCard(){
		int yopCard = oldDevCards.getYearOfPlenty();
		if(yopCard > 0){
			return true;
		}
		return false;
	}
	
	public boolean canPlayMonopolyCard(){
		int monopolyCard = oldDevCards.getMonopoly();
		if(monopolyCard > 0){
			return true;
		}
		return false;
	}
	
	public boolean canPlaySoldierCard(){
		int soldierCard = oldDevCards.getSoldier();
		if(soldierCard > 0){
			return true;
		}
		return false;
	}
	

	
	
	
	
}
