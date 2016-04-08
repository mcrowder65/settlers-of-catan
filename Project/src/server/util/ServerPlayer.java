package server.util;

import client.utils.Translator;
import shared.definitions.CatanColor;
import shared.definitions.DevCardList;
import shared.definitions.DevCardType;
import shared.definitions.Player;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;
import shared.definitions.TradeOffer;
import shared.locations.*;
/**
 * class that holds all the attributes of a player for the server
 * inherits from the Player
 * @author Brennen
 *
 */
public class ServerPlayer extends Player {

	
	
	public ServerPlayer() {
		this.setRoads(15);
		this.setCities(4);
		this.setSettlements(5);
		
		//TODO: Maybe wrong (eric)
		this.setOldDevCards(new DevCardList());
		this.setNewDevCards(new DevCardList());
		this.setResources(new ResourceList());
	}
	/**
	 * constructor for ServerPlayer
	 * @param name
	 * @param color
	 * @param playerID
	 * @param playerIndex
	 * @throws IllegalArgumentException
	 */
	public ServerPlayer(String name, CatanColor color, int playerID, int playerIndex) throws IllegalArgumentException {
		super(name,color, playerID, playerIndex);
		this.setRoads(15);
		this.setCities(4);
		this.setSettlements(5);
		
		
	}
	
	/**
	 * constructor to make a serverplayer from another player
	 * @param copy
	 */
	public ServerPlayer(ServerPlayer copy){
		super(copy.getCities(), copy.getColor(), copy.getDiscarded(), 
				copy.getMonuments(), copy.getName(), copy.getNewDevCards(),
				copy.getOldDevCards(), copy.getPlayerIndex(), copy.getPlayedDevCard(),
				copy.getPlayerID(), copy.getResources(), copy.getRoads(), copy.getSettlements(),
				copy.getSoldiers(), copy.getVictoryPoints());
	}


	/**
	 * lays a normal road for the player
	 */
	public void layRoad(){
		chargeBasicRoad();
		removeRoad();
	}
	
	/**
	 * updates player for using roadbuilder
	 */
	public void layRoadBuilder(){
		playRoadBuilderCard();
		removeRoad();
		removeRoad();
	}

	/**
	 * updates player when they lay a settlement during normal play
	 */
	public void laySettlement(){
		chargeBasicSettlement();
		setVictoryPoints(getVictoryPoints()+1);
		removeSettlement();
	}
	
	/**
	 * checks to see if a player can make the trade offer that the client sent over
	 * @param offer
	 * @return
	 */
	public boolean canMakeTrade(ResourceList offer){
		if(offer.getBrick() > getResources().getBrick()){
			return false;
		}
		if(offer.getWheat() > getResources().getWheat()){
			return false;
		}
		if(offer.getOre() > getResources().getOre()){
			return false;
		}
		if(offer.getSheep() > getResources().getSheep()){
			return false;
		}
		if(offer.getWood() > getResources().getWood()){
			return false;
		}
		
		return true;
	}
	
	/**
	 * Discards the card sent in by the server
	 * @param cards
	 */
	public void discardCards(ResourceList cards){
 		int wood = cards.getWood();
 		int brick = cards.getBrick();
 		int wheat = cards.getWheat();
 		int sheep = cards.getSheep();
 		int ore = cards.getOre();
 		
 		if(wood > 0){
 			getResources().removeResource(ResourceType.WOOD, wood);
 		}
 		if(brick > 0){
 			getResources().removeResource(ResourceType.BRICK, brick);
 		}
 		if(wheat > 0){
 			getResources().removeResource(ResourceType.WHEAT, wheat);
 		}
 		if(sheep > 0){
 			getResources().removeResource(ResourceType.SHEEP, sheep);
 		}
 		if(ore > 0){
 			getResources().removeResource(ResourceType.ORE, ore);
 		}
 		setDiscarded(true);
 	}
	
	/**
	 * checks to see if the player can accept the trade sent over
	 * @param offer
	 * @return
	 */
	public boolean canAcceptTrade(ResourceList offer){
		ResourceList resources = this.getResources(); //gets resources
		if(offer.getBrick()>0){
			if(resources.getBrick() < offer.getBrick()){
				return false;
			}
		}
		if(offer.getWheat()>0){
			if(resources.getWheat() < offer.getWheat()){
				return false;
			}
		}
		if(offer.getWood()>0){
			if(resources.getWood() < offer.getWood()){
				return false;
			}
		}
		if(offer.getOre()>0){
			if(resources.getOre() < offer.getOre()){
				return false;
			}
		}
		if(offer.getSheep()>0){
			if(resources.getSheep() < offer.getSheep()){
				return false;
			}
		}
		return true;
	}
	/**
	 * updates a player when they lay a city during normal play
	 */
	public void layCity(){
		chargeBasicCity();
		removeCity();
		setVictoryPoints(getVictoryPoints()+1);
		addSettlement();
	}
	
	/**
	 * adds a recource to the player's resource list
	 * @param resource
	 */
	public void addResource(ResourceType resource){
		if(resource == ResourceType.WOOD){
			addWood();
		}
		if(resource == ResourceType.BRICK){
			addBrick();
		}
		if(resource == ResourceType.WHEAT){
			addWheat();
		}
		if(resource == ResourceType.SHEEP){
			addSheep();
		}
		if(resource == ResourceType.ORE){
			addOre();
		}
		
	}
	
	/**
	 * removes a resource from the player's resource list
	 * @param resource
	 */
	public void removeResource(ResourceType resource){
		if(resource == ResourceType.WOOD){
			removeWood();
		}
		if(resource == ResourceType.BRICK){
			removeBrick();
		}
		if(resource == ResourceType.WHEAT){
			removeWheat();
		}
		if(resource == ResourceType.SHEEP){
			removeSheep();
		}
		if(resource == ResourceType.ORE){
			removeOre();
		}
		
	}
	
	/**
	 * adds a brick resource to the player's resource List
	 */
	public void addBrick(){
		ResourceList allResources = getResources();
		int brick = allResources.getBrick();
		brick++;
		allResources.setBrick(brick);
		setResources(allResources);
	}
	/**
	 * adds a wheat to the player's resource list
	 */
	public void addWheat(){
		ResourceList allResources = getResources();
		int wheat = allResources.getWheat(); //getting wheat
		wheat++; //increasing wheat
		allResources.setWheat(wheat); //setting wheat
		setResources(allResources);
	}
	/**
	 * adds a sheep to the player's resource list
	 */
	public void addSheep(){
		ResourceList allResources = getResources();
		int sheep = allResources.getSheep();
		sheep++;
		allResources.setSheep(sheep);
		setResources(allResources);
	}
	/**
	 * adds a wood to the player's resource list
	 */
	public void addWood(){
		ResourceList allResources = getResources();
		int wood = allResources.getWood();
		wood++;
		allResources.setWood(wood);
		setResources(allResources);
	}
	/**
	 * adds an ore to the player's resource list
	 */
	public void addOre(){
		ResourceList allResources = getResources();
		int ore = allResources.getOre();
		ore++;
		allResources.setOre(ore);
		setResources(allResources);
	}
	
	/**
	 * removes an ore from the player's resource list
	 */
	public void removeBrick(){
		ResourceList allResources = getResources();
		int brick = allResources.getBrick();
		brick--;
		allResources.setBrick(brick);
		setResources(allResources);
	}
	/**
	 * removes a wheat from the player's resource list
	 */
	public void removeWheat(){
		ResourceList allResources = getResources();
		int wheat = allResources.getWheat();
		wheat--;
		allResources.setWheat(wheat);
		setResources(allResources);
	}
	/**
	 * removes a sheep from the player's resource list
	 */
	public void removeSheep(){
		ResourceList allResources = getResources();
		int sheep = allResources.getSheep();
		sheep--;
		allResources.setSheep(sheep);
		setResources(allResources);
	}
	/**
	 * removes a wood from the player's resource list
	 */
	public void removeWood(){
		ResourceList allResources = getResources();
		int wood = allResources.getWood();
		wood--;
		allResources.setWood(wood);
		setResources(allResources);
	}
	/**
	 * removes a ore from the player's resource list
	 */
	public void removeOre(){
		ResourceList allResources = getResources();
		int ore = allResources.getOre();
		ore--;
		allResources.setOre(ore);
		setResources(allResources);
	}
	
	/**
	 * adds a roadbuilder to the player's dev cards
	 */
	public void addRoadBuilder(){
		DevCardList newDevCards = getNewDevCards();
		int roadBuilder = newDevCards.getRoadBuilding();
		roadBuilder++;
		newDevCards.setRoadBuilding(roadBuilder);
		setNewDevCards(newDevCards);
	}
	/**
	 * adds a soldier card to the player's dev cards
	 */
	public void addSoldierCard(){
		DevCardList newDevCards = getNewDevCards();
		int soldier = newDevCards.getSoldier();
		soldier++;
		newDevCards.setSoldier(soldier);
		setNewDevCards(newDevCards);
	}
	/**
	 * adds a monopoly card to the player's dev cards
	 */
	public void addMonopoly(){
		DevCardList newDevCards = getNewDevCards();
		int monopoly = newDevCards.getMonopoly();
		monopoly++;
		newDevCards.setMonopoly(monopoly);
		setNewDevCards(newDevCards);
	}
	/**
	 * adds a yop card to the player's dev cards
	 */
	public void addYearOfPlenty(){
		DevCardList newDevCards = getNewDevCards();
		int YOP = newDevCards.getYearOfPlenty(); //gets old yop amt
		YOP++; //increases yop amt
		newDevCards.setYearOfPlenty(YOP); //sets yop amt
		setNewDevCards(newDevCards); //updates cards
	}
	/**
	 * adds a monument card to the player's dev cards
	 */
	public void addMonument(){
		DevCardList newDevCards = getNewDevCards();
		DevCardList oldDevCards = getOldDevCards();
		int monument = newDevCards.getMonument();
		monument = monument + oldDevCards.getMonument(); //getting monument card amt
		monument++; //increase monuments
		oldDevCards.setMonument(monument); //sets monuments
		setOldDevCards(oldDevCards); //updates list
	}
	/**
	 * updates the player when a monument card is played
	 */
	public void playMonument(){
		removeMonument();
		addVictoryPoints();
	}
	/**
	 * removes the resources for purchasing a dev card
	 */
	public void chargeBuyDevCard(){
		removeOre();
		removeSheep();
		removeWheat();
	}
	
	/**
	 * buys a dev card for the player
	 * adds the card to player's dev card list
	 * @param card
	 */
	public void buyDevCard(DevCardType card){
		chargeBuyDevCard(); //removes resources 
		
		//adds the card that player bought
		if(card == DevCardType.SOLDIER){
			addSoldierCard();
		}
		if(card == DevCardType.YEAR_OF_PLENTY){
			addYearOfPlenty();
		}
		if(card == DevCardType.MONOPOLY){
			addMonopoly();
		}
		if(card == DevCardType.ROAD_BUILD){
			addRoadBuilder();
		}
		if(card == DevCardType.MONUMENT){
			addMonument();
		}
	}
	/**
	 * checks to see if a player has the resouces to build a road
	 * @return true or false
	 */
	public boolean resourcesToBuildRoad(){		
		int brick = getResources().getBrick();		
		int wood = getResources().getWood();		
		int roads = getRoads();		
		if(brick >0 && wood>0 && roads>0){		
			return true; //player has the necessary resources		
		}		
		return false;	//player doesn't have the necessary resources	
	}

	
	
}
