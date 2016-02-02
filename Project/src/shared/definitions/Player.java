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
	 * number of armyPoints the player has (Soldier Card)
	 */
	private int armyPoints;
	
	/**
	 * Whether or not this player has used a DevCard this turn (preventing it from using another one
	 * in the same turn)
	 * True = The player has used a Dev Card this turn
	 * False = The player hasn't used a Dev Card this turn
	 */
	private boolean usedDevCardThisTurn;
	
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
	public Player(CatanColor color, String name, int playerID){
		this.color = color;
		this.name = new String(name);
		this.playerID = playerID;
	}
	public Player(){
		
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
	
	public void setUsedDevCardThisTurn(boolean value){
		this.usedDevCardThisTurn = value;
	}
	
	public boolean getUsedDevCardThisTurn(){
		return this.usedDevCardThisTurn;
	}
	
	public void addRoad(){
		roads++;
	}
	public void addSettlement(){
		settlements++;
	}
	public void addCity(){
		cities++;
	}

	public void removeSettlement(){
		settlements = settlements -1;
	}
	
	public boolean hasMoreThanSeven(){
		int brick = resources.getBrick();
		int wood = resources.getWood();
		int sheep = resources.getSheep();
		int grain = resources.getWheat();
		int ore = resources.getOre();
		
		int total = brick + wood + sheep + grain + ore;
		if(total > 7){
			return true;
		}
		
		return false;
	}
	
	public void removeOldDevCard(DevCardList devCard){
		int soldier = oldDevCards.getSoldier();
		int monument = oldDevCards.getMonument();
		int monopoly = oldDevCards.getMonopoly();
		int roadBuilding = oldDevCards.getRoadBuilding();
		int yearOfPlenty = oldDevCards.getYearOfPlenty();
		
		int newSoldier = devCard.getSoldier();
		int newMonument = devCard.getMonument();
		int newMonopoly = devCard.getMonopoly();
		int newRoadBuilding = devCard.getRoadBuilding();
		int newYearOfPlenty = devCard.getYearOfPlenty();
		
		if(newSoldier > 0){
			soldier = soldier -1;
			oldDevCards.setSoldier(soldier);
		
		}
		if(newMonument > 0){
			monument = monument -1;
			oldDevCards.setMonument(monument);
		}
		if(newMonopoly > 0){
			monopoly = monopoly -1;
			oldDevCards.setMonument(monopoly);
		}
		if(newRoadBuilding > 0){
			roadBuilding = roadBuilding -1;
			oldDevCards.setMonument(roadBuilding);
		}
		if(newYearOfPlenty > 0){
			yearOfPlenty = yearOfPlenty -1;
			oldDevCards.setMonument(yearOfPlenty);
		}
		
	}
	
	public void chargeBasicRoad(){
		int brick = resources.getBrick();
		int wood = resources.getWood();
		
		brick = brick -1;
		wood = wood -1;
		resources.setWood(wood);
		resources.setBrick(brick);
		
	}
	
	public void chargeBasicSettlement(){
		int brick = resources.getBrick();
		int wood = resources.getWood();
		int sheep = resources.getSheep();
		int grain = resources.getWheat();
		
		brick = brick -1;
		wood = wood -1;
		sheep = sheep -1;
		grain = grain -1;
		
		resources.setBrick(brick);
		resources.setWood(wood);
		resources.setSheep(sheep);
		resources.setWheat(grain);
			
	}
	
	public void chargeBasicCity(){
		int ore = resources.getOre();
		int wheat = resources.getWheat();
		
		ore = ore -3;
		wheat = wheat -2;
		
		resources.setOre(ore);
		resources.setWheat(wheat);
	}
	
	public void chargeBasicDevCard(){
		int ore = resources.getOre();
		int sheep = resources.getSheep();
		int wheat = resources.getWheat();
		
		ore = ore - 1;
		sheep = sheep -1;
		wheat = wheat -1;
		
		resources.setOre(ore);
		resources.setSheep(sheep);
		resources.setWheat(wheat);
		
	}
	
	
	public boolean canBuyRoad(){
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
	
	public boolean canBuySettlement(){
		int brick = resources.getBrick();
		int wood = resources.getWood();
		int sheep = resources.getSheep();
		int grain = resources.getWheat();
		
		if(brick>0 && wood>0 && sheep>0 && grain>0){
			return true;
		}
		return false;
	}
	
	public boolean canBuyCity(){
		int ore = resources.getOre();
		int grain = resources.getWheat();
		
		if(ore>3 && grain>2){
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
	
	public void addDevCard(DevCardList devCard){
		int soldier = newDevCards.getSoldier();
		int monument = oldDevCards.getMonument();
		int monopoly = newDevCards.getMonopoly();
		int roadBuilding = newDevCards.getRoadBuilding();
		int yearOfPlenty = newDevCards.getYearOfPlenty();
		
		int newSoldier = devCard.getSoldier();
		int newMonument = devCard.getMonument();
		int newMonopoly = devCard.getMonopoly();
		int newRoadBuilding = devCard.getRoadBuilding();
		int newYearOfPlenty = devCard.getYearOfPlenty();
		
		soldier = soldier + newSoldier;
		monument = monument + newMonument;
		monopoly = monopoly + newMonopoly;
		roadBuilding = roadBuilding + newRoadBuilding;
		yearOfPlenty = yearOfPlenty + newYearOfPlenty;
		
		newDevCards.setSoldier(soldier);
		oldDevCards.setMonument(monument);
		newDevCards.setMonopoly(monopoly);
		newDevCards.setRoadBuilding(roadBuilding);
		newDevCards.setYearOfPlenty(yearOfPlenty);
			
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
	
	public boolean canPlayMonumentCard(){
		int monumentCard = oldDevCards.getMonument();
		monumentCard = monumentCard + newDevCards.getMonument();
		if(monumentCard > 0){
			int totalPoints = victoryPoints + monumentCard;
			if(totalPoints >= 10){
				return true;
			}
			
		}
		return false;
	}
	
	public boolean canPlayDevCard(){
		if(this.playedDevCard == true){
			return false;
		}
		int devCards = oldDevCards.getMonument() + oldDevCards.getSoldier() + oldDevCards.getMonopoly() + oldDevCards.getRoadBuilding() + oldDevCards.getYearOfPlenty();
		if(devCards > 0){
			return true;
		}
		return false;
	}
	
	public void playSoldierCard(){
		int soldierCard = oldDevCards.getSoldier();
		oldDevCards.setSoldier(soldierCard-1);
		armyPoints++;
		this.setUsedDevCardThisTurn(true);
	}
	public void playMonopolyCard(){
		int monopolyCard = oldDevCards.getMonopoly();
		oldDevCards.setMonopoly(monopolyCard-1);
		this.setUsedDevCardThisTurn(true);
	}
	public void playYearOfPlentyCard(){
		int yearOfPlentyCard = oldDevCards.getYearOfPlenty();
		oldDevCards.setYearOfPlenty(yearOfPlentyCard-1);
		this.setUsedDevCardThisTurn(true);
	}
	
	
	public void updateOldDevCard(){
		int soldier = oldDevCards.getSoldier();
		int monument = oldDevCards.getMonument();
		int monopoly = oldDevCards.getMonopoly();
		int roadBuilding = oldDevCards.getRoadBuilding();
		int yearOfPlenty = oldDevCards.getYearOfPlenty();
		int soldierTotal = soldier + newDevCards.getSoldier();
		int monumentTotal = soldier + newDevCards.getMonument();
		int monopolyTotal = soldier + newDevCards.getMonopoly();
		int roadBuildingTotal = soldier + newDevCards.getRoadBuilding();
		int yopTotal = soldier + newDevCards.getYearOfPlenty();
		
		oldDevCards.setSoldier(soldierTotal);
		oldDevCards.setMonument(monumentTotal);
		oldDevCards.setMonopoly(monopolyTotal);
		oldDevCards.setRoadBuilding(roadBuildingTotal);
		oldDevCards.setYearOfPlenty(yopTotal);
		
		newDevCards.setSoldier(0);
		newDevCards.setMonument(0);
		newDevCards.setMonopoly(0);
		newDevCards.setRoadBuilding(0);
		newDevCards.setYearOfPlenty(0);
		
	}
	
	public boolean canAcceptTrade(ResourceList offer){
		int brickWanted = offer.getBrick();
		int sheepWanted = offer.getSheep();
		int woodWanted = offer.getWood();
		int oreWanted = offer.getOre();
		int wheatWanted = offer.getWheat();
		int brick = resources.getBrick();
		int sheep = resources.getSheep();
		int wood = resources.getWood();
		int ore = resources.getOre();
		int wheat = resources.getWheat();
		
		if(brick >= brickWanted && sheep >= sheepWanted && wood>=woodWanted && ore>=oreWanted && wheat>=wheatWanted){
			return true;
		}
		return false;
	}
	
	public boolean canOfferTrade(){
		int total = resources.getWheat() + resources.getOre() + resources.getWood() + resources.getSheep() + resources.getBrick();
		if(total > 0){
			return true;
		}
		return false;
	}
	
	public boolean canDiscardCards(){

		int total = resources.getWheat() + resources.getOre() + resources.getWood() + resources.getSheep() + resources.getBrick();
		if(total > 0){
			return true;
		}
		return false;
	}
	
	public void removeResourceCards(ResourceList resourceList){
		int brick = resources.getBrick();
		int sheep = resources.getSheep();
		int ore = resources.getOre();
		int wood = resources.getWood();
		int wheat = resources.getWheat();
		
		int brickTemp = resourceList.getBrick();
		int sheepTemp = resourceList.getSheep();
		int oreTemp = resourceList.getOre();
		int woodTemp = resourceList.getWood();
		int wheatTemp = resourceList.getWheat();
		
		brick = brick - brickTemp;
		sheep = sheep - sheepTemp;
		ore = ore - oreTemp;
		wood = wood - woodTemp;
		wheat = wheat - wheatTemp;
		
		resources.setBrick(brick);
		resources.setSheep(sheep);
		resources.setOre(ore);
		resources.setWood(wood);
		resources.setWheat(wheat);
		
	}
	
	public void addResourceCards(ResourceList resourceList){
		int brick = resources.getBrick();
		int sheep = resources.getSheep();
		int ore = resources.getOre();
		int wood = resources.getWood();
		int wheat = resources.getWheat();
		
		int brickTemp = resourceList.getBrick();
		int sheepTemp = resourceList.getSheep();
		int oreTemp = resourceList.getOre();
		int woodTemp = resourceList.getWood();
		int wheatTemp = resourceList.getWheat();
		
		brick = brick + brickTemp;
		sheep = sheep + sheepTemp;
		ore = ore + oreTemp;
		wood = wood + woodTemp;
		wheat = wheat + wheatTemp;
		
		resources.setBrick(brick);
		resources.setSheep(sheep);
		resources.setOre(ore);
		resources.setWood(wood);
		resources.setWheat(wheat);
	}
	
	
	
	
}
