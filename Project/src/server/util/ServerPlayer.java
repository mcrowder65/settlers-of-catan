package server.util;

import client.utils.Translator;
import shared.definitions.CatanColor;
import shared.definitions.DevCardList;
import shared.definitions.Player;
import shared.definitions.ResourceList;
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
	/**
	 * updates a player when they lay a city during normal play
	 */
	public void layCity(){
		chargeBasicCity();
		removeCity();
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
	
	
	
	
	
	
}