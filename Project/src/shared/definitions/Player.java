package shared.definitions;

import client.utils.Translator;
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
	private boolean discarded;
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
	private boolean playedDevCard;
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
		this.name = name;
		this.color = color;
		this.playerID = playerID;
		this.playerIndex = playerIndex;
	
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
	public int getCities() throws IllegalArgumentException{
		return cities;
	}

	/**
	 * retrieves color of the player
	 * @return color
	 */
	public CatanColor getColor() throws IllegalArgumentException{
		return color;
	}

	/**
	 * retrieves discarded player attribute
	 * @return discarded
	 */
	public boolean getDiscarded() throws IllegalArgumentException{
		return discarded;
	}

	/**
	 * retrieves number of monuments 
	 * @return monuments
	 */
	public int getMonuments() throws IllegalArgumentException{
		return monuments;
	}

	/**
	 * retrieves name of player
	 * @return name
	 */
	public String getName() throws IllegalArgumentException{
		return name;
	}

	/**
	 * retrieves list of Dev cards that cannot be played until the player's next turn
	 * @return newDevCards
	 */
	public DevCardList getNewDevCards() throws IllegalArgumentException{
		return newDevCards;
	}

	/**
	 * retrieves list of dev cards that are currently playable
	 * @return oldDevCards
	 */
	public DevCardList getOldDevCards() throws IllegalArgumentException{
		return oldDevCards;
	}

	/**
	 * retrieves the playerIndex
	 * @return playerIndex
	 */
	public int getPlayerIndex() throws IllegalArgumentException{
		return playerIndex;
	}

	/**
	 * retrieves whether or not the player used any dev cards
	 * @return playerDevCard
	 */
	public Boolean getPlayedDevCard() throws IllegalArgumentException{
		return playedDevCard;
	}

	/**
	 * retrieves the id of the player
	 * @return playerID
	 */
	public int getPlayerID() throws IllegalArgumentException{
		return playerID;
	}

	/**
	 * retrieves list of resources that the player has
	 * @return resources
	 */
	public ResourceList getResources() throws IllegalArgumentException{
		return resources;
	}

	/**
	 * retrieves number of roads the player has
	 * @return roads
	 */
	public int getRoads() throws IllegalArgumentException{
		return roads;
	}

	/**
	 * retrieves number of settlements
	 * @return settlements
	 */
	public int getSettlements() throws IllegalArgumentException{
		return settlements;
	}

	/**
	 * retrieves number of soldiers
	 * @return soldiers
	 */
	public int getSoldiers() throws IllegalArgumentException{
		return soldiers;
	}

	/**
	 * retrieves number of victory points the player has
	 * @return victoryPoints
	 */
	public int getVictoryPoints() throws IllegalArgumentException{
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
	public void setColor(CatanColor color)  throws IllegalArgumentException {
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

	public void removeMonument() throws IllegalArgumentException  {
		this.monuments++;
		int monumentCard = oldDevCards.getMonument();
		monumentCard--;
		oldDevCards.setMonument(monumentCard);
	}
	public void addVictoryPoints() throws IllegalArgumentException  {
		this.victoryPoints++;
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
		if(brick >0 && wood>0 && roads>0){
			if(roads > 0){
				return true;
			}
		}
		//int roadBuildingCard = oldDevCards.getRoadBuilding();
		//if(roadBuildingCard > 0){
			//return true;
		//}
		
		return false;
	}
	
	public void addRoad() throws IllegalArgumentException{
		roads++;
	}
	public void addSettlement() throws IllegalArgumentException{
		settlements++;
	}
	public void addCity(){
		cities++;
	}

	public void removeSettlement() throws IllegalArgumentException{
		settlements = settlements -1;
	}
	public void removeRoad() throws IllegalArgumentException{
		roads = roads -1;
	}
	public void removeCity() throws IllegalArgumentException{
		cities = cities -1;
	}
	
	public int getNumOfCards(){
		int brick = resources.getBrick();
		int wood = resources.getWood();
		int sheep = resources.getSheep();
		int grain = resources.getWheat();
		int ore = resources.getOre();
		
		int total = brick + wood + sheep + grain + ore;
		return total;
	}
	
	public void removeOldDevCard(DevCardList devCard) throws IllegalArgumentException{
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
	
	public void chargeBasicRoad() throws IllegalArgumentException{
		int brick = resources.getBrick();
		int wood = resources.getWood();
		
		brick = brick -1;
		wood = wood -1;
		resources.setWood(wood);
		resources.setBrick(brick);
		
	}
	
	public void chargeBasicSettlement() throws IllegalArgumentException{
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
	
	public void chargeBasicCity() throws IllegalArgumentException{
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
	
	
	public boolean canBuildSettlement() throws IllegalArgumentException{
		int brick = resources.getBrick();
		int wood = resources.getWood();
		int sheep = resources.getSheep();
		int grain = resources.getWheat();
		
		if(brick>0 && wood>0 && sheep>0 && grain>0 && settlements>0){
			return true;
		}
		return false;
	}
	
	public boolean canBuildCity() throws IllegalArgumentException{
		int ore = resources.getOre();
		int grain = resources.getWheat();
		
		if(ore>2 && grain>1 && cities>0){
			return true;
		}
		return false;
	}
	
	public boolean canBuyDevCard() throws IllegalArgumentException{
		int ore = resources.getOre();
		int sheep = resources.getSheep();
		int grain = resources.getWheat();
		if(ore>0 && sheep>0 && grain >0){
			return true;
		}
		
		return false;
	}
	
	public void addDevCard(DevCardList devCard) throws IllegalArgumentException{
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
	
	public boolean canPlayRoadBuilding() throws IllegalArgumentException{
		if(this.playedDevCard == false){
			int roadBuildingCard = oldDevCards.getRoadBuilding();
			if(roadBuildingCard > 0){
				if(roads >1){
					return true;
				}
			}
			
		}
		return false;
	}
	
	public boolean canPlayYearOfPlentyCard() throws IllegalArgumentException{
		if(this.playedDevCard == false){
			int yopCard = oldDevCards.getYearOfPlenty();
			if(yopCard > 0){
				return true;
			}
		}
		return false;
	}
	
	public boolean canPlayMonopolyCard() throws IllegalArgumentException{
		if(this.playedDevCard == false){
			int monopolyCard = oldDevCards.getMonopoly();
			if(monopolyCard > 0){
				return true;
			}
		}
		return false;
	}
	
	public boolean canPlaySoldierCard() throws IllegalArgumentException{
		if(this.playedDevCard == false){
			int soldierCard = oldDevCards.getSoldier();
			if(soldierCard > 0){
				return true;
			}
		}
		return false;
	}
	
	public boolean canPlayMonumentCard() throws IllegalArgumentException{
		return (oldDevCards.getMonument() + newDevCards.getMonument() + this.getVictoryPoints() >= 10);
	}
	
	public boolean canPlayDevCard() throws IllegalArgumentException{
		if(this.playedDevCard == true){
			return false;
		}
		int devCards = oldDevCards.getMonument() + oldDevCards.getSoldier() + oldDevCards.getMonopoly() + oldDevCards.getRoadBuilding() + oldDevCards.getYearOfPlenty();
		if(devCards > 0){
			return true;
		}
		return false;
	}
	
	public void playSoldierCard() throws IllegalArgumentException{
		int soldierCard = oldDevCards.getSoldier();
		soldierCard--;
		oldDevCards.setSoldier(soldierCard);
	}
	public void playMonopolyCard(){
		int monopolyCard = oldDevCards.getMonopoly();
		monopolyCard--;
		oldDevCards.setMonopoly(monopolyCard);
	}
	public void playYearOfPlentyCard(){
		int yearOfPlentyCard = oldDevCards.getYearOfPlenty();
		yearOfPlentyCard--;
		oldDevCards.setYearOfPlenty(yearOfPlentyCard);
	}
	public void playRoadBuilderCard(){
		int roadBuilder = oldDevCards.getRoadBuilding();
		roadBuilder--;
		oldDevCards.setRoadBuilding(roadBuilder);
	}
	
	
	public void updateOldDevCard() throws IllegalArgumentException{
		int soldier = oldDevCards.getSoldier();
		int monument = oldDevCards.getMonument();
		int monopoly = oldDevCards.getMonopoly();
		int roadBuilding = oldDevCards.getRoadBuilding();
		int yearOfPlenty = oldDevCards.getYearOfPlenty();
		int soldierTotal = soldier + newDevCards.getSoldier();
		int monumentTotal = monument + newDevCards.getMonument();
		int monopolyTotal = monopoly + newDevCards.getMonopoly();
		int roadBuildingTotal = roadBuilding + newDevCards.getRoadBuilding();
		int yopTotal = yearOfPlenty + newDevCards.getYearOfPlenty();
		
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
	
	public boolean canAcceptTrade(TradeOffer tradeOffer) throws IllegalArgumentException{
		ResourceList offer = tradeOffer.getOffer();
		
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
		boolean canAccept = true;
		if(brickWanted < 0){
			if(Math.abs(brickWanted) > brick) //reverse
				canAccept = false;
		}
		if(sheepWanted < 0){
			if(Math.abs(sheepWanted) > sheep)
				canAccept = false;
		}
		if(woodWanted < 0){
			if(Math.abs(woodWanted) > wood)
				canAccept = false;
		}
		if(oreWanted < 0){
			if(Math.abs(oreWanted) > ore)
				canAccept = false;
		}
		if(wheatWanted < 0){
			if(Math.abs(wheatWanted) > wheat)
				canAccept = false;
		}
		return canAccept;
	}
	
	
	public boolean canOfferTrade() throws IllegalArgumentException{
		int total = resources.getWheat() + resources.getOre() + resources.getWood() + resources.getSheep() + resources.getBrick();
		if(total > 0){
			return true;
		}
		return false;
	}
	
	public boolean canDiscardCards() throws IllegalArgumentException{

		int total = resources.getWheat() + resources.getOre() + resources.getWood() + resources.getSheep() + resources.getBrick();
		if(total > 0){
			return true;
		}
		return false;
	}
	
	public void removeResourceCards(ResourceList resourceList) throws IllegalArgumentException{
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
	
	public void addResourceCards(ResourceList resourceList) throws IllegalArgumentException{
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
	
	/**
	 * Determines if the Player has enough resources for a date
	 * @return True if there are enough resources
	 */
	public boolean enoughResourceCardsToTrade() throws IllegalArgumentException{
		if(resources.getBrick() >= 2) return true;
		else if(resources.getOre() >= 2) return true;
		else if(resources.getSheep() >= 2) return true;
		else if(resources.getWheat() >= 2) return true;
		else if(resources.getWood() >= 2) return true;
		
		return false;
	}
	
	public boolean hasDevCards() {
		if(this.oldDevCards.isEmpty() && this.newDevCards.isEmpty()) {
			return false;
		}
		else return true;
	}
	
	@Override
	public int hashCode() throws IllegalArgumentException{
		final int prime = 31;
		int result = 1;
		result = prime * result + cities;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + ((discarded == false) ? 0 : 1);
		result = prime * result + monuments;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((newDevCards == null) ? 0 : newDevCards.hashCode());
		result = prime * result + ((oldDevCards == null) ? 0 : oldDevCards.hashCode());
		result = prime * result + ((playedDevCard == false) ? 0 : 1);
		result = prime * result + playerID;
		result = prime * result + playerIndex;
		result = prime * result + ((resources == null) ? 0 : resources.hashCode());
		result = prime * result + roads;
		result = prime * result + settlements;
		result = prime * result + soldiers;
		result = prime * result + victoryPoints;
		return result;
	}
	@Override
	public boolean equals(Object obj) throws IllegalArgumentException {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (cities != other.cities)
			return false;
		if (color != other.color)
			return false;
		if (discarded != other.discarded) {
				return false;
		} 
		if (monuments != other.monuments)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (newDevCards == null) {
			if (other.newDevCards != null)
				return false;
		} else if (!newDevCards.equals(other.newDevCards))
			return false;
		if (oldDevCards == null) {
			if (other.oldDevCards != null)
				return false;
		} else if (!oldDevCards.equals(other.oldDevCards))
			return false;
		if (!playedDevCard != other.playedDevCard)
			return false;
		if (playerID != other.playerID)
			return false;
		if (playerIndex != other.playerIndex)
			return false;
		if (resources == null) {
			if (other.resources != null)
				return false;
		} else if (!resources.equals(other.resources))
			return false;
		if (roads != other.roads)
			return false;
		if (settlements != other.settlements)
			return false;
		if (soldiers != other.soldiers)
			return false;
		if (victoryPoints != other.victoryPoints)
			return false;
		return true;
	}
	
	
	
}
