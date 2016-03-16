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
		removeSettlement();
	}
	
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
	
	public boolean canAcceptTrade(ResourceList offer){
		ResourceList resources = this.getResources();
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
	}
	
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
	
	public void addBrick(){
		ResourceList allResources = getResources();
		int brick = allResources.getBrick();
		brick++;
		allResources.setBrick(brick);
		setResources(allResources);
	}
	public void addWheat(){
		ResourceList allResources = getResources();
		int wheat = allResources.getWheat();
		wheat++;
		allResources.setWheat(wheat);
		setResources(allResources);
	}
	public void addSheep(){
		ResourceList allResources = getResources();
		int sheep = allResources.getSheep();
		sheep++;
		allResources.setBrick(sheep);
		setResources(allResources);
	}
	public void addWood(){
		ResourceList allResources = getResources();
		int wood = allResources.getWood();
		wood++;
		allResources.setBrick(wood);
		setResources(allResources);
	}
	public void addOre(){
		ResourceList allResources = getResources();
		int ore = allResources.getOre();
		ore++;
		allResources.setBrick(ore);
		setResources(allResources);
	}
	
	public void removeBrick(){
		ResourceList allResources = getResources();
		int brick = allResources.getBrick();
		brick--;
		allResources.setBrick(brick);
		setResources(allResources);
	}
	public void removeWheat(){
		ResourceList allResources = getResources();
		int wheat = allResources.getWheat();
		wheat--;
		allResources.setWheat(wheat);
		setResources(allResources);
	}
	public void removeSheep(){
		ResourceList allResources = getResources();
		int sheep = allResources.getSheep();
		sheep--;
		allResources.setBrick(sheep);
		setResources(allResources);
	}
	public void removeWood(){
		ResourceList allResources = getResources();
		int wood = allResources.getWood();
		wood--;
		allResources.setBrick(wood);
		setResources(allResources);
	}
	public void removeOre(){
		ResourceList allResources = getResources();
		int ore = allResources.getOre();
		ore--;
		allResources.setBrick(ore);
		setResources(allResources);
	}
	
	public void addRoadBuilder(){
		DevCardList oldDevCards = getOldDevCards();
		int roadBuilder = oldDevCards.getRoadBuilding();
		roadBuilder++;
		oldDevCards.setRoadBuilding(roadBuilder);
		setOldDevCards(oldDevCards);
	}
	
	public void addSoldierCard(){
		DevCardList oldDevCards = getOldDevCards();
		int soldier = oldDevCards.getSoldier();
		soldier++;
		oldDevCards.setRoadBuilding(soldier);
		setOldDevCards(oldDevCards);
	}
	
	public void addMonopoly(){
		DevCardList oldDevCards = getOldDevCards();
		int monopoly = oldDevCards.getMonopoly();
		monopoly++;
		oldDevCards.setRoadBuilding(monopoly);
		setOldDevCards(oldDevCards);
	}
	
	public void addYearOfPlenty(){
		DevCardList oldDevCards = getOldDevCards();
		int YOP = oldDevCards.getYearOfPlenty();
		YOP++;
		oldDevCards.setRoadBuilding(YOP);
		setOldDevCards(oldDevCards);
	}
	
	public void addMonument(){
		DevCardList newDevCards = getNewDevCards();
		DevCardList oldDevCards = getOldDevCards();
		int monument = newDevCards.getMonument();
		monument = monument + oldDevCards.getMonument();
		monument++;
		newDevCards.setRoadBuilding(monument);
		setNewDevCards(newDevCards);
	}
	
	public void playMonument(){
		removeMonument();
		addVictoryPoints();
	}
	
	public void chargeBuyDevCard(){
		removeOre();
		removeSheep();
		removeWheat();
	}
	
	public void buyDevCard(DevCardType card){
		chargeBuyDevCard();
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
	
	public boolean resourcesToBuildRoad(){		
		int brick = getResources().getBrick();		
		int wood = getResources().getWood();		
		int roads = getRoads();		
		if(brick >0 && wood>0 && roads>0){		
			return true;		
		}		
		return false;		
	}
	
	
	
}
