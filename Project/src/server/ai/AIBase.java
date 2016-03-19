package server.ai;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import client.data.RobPlayerInfo;
import server.Game;
import server.facade.*;
import server.util.ServerGameMap;
import server.util.ServerPlayer;
import shared.communication.response.GetModelResponse;
import shared.definitions.CatanColor;
import shared.definitions.Hex;
import shared.definitions.Player;
import shared.definitions.ResourceList;
import shared.definitions.ResourceType;
import shared.locations.EdgeLocation;
import shared.locations.EdgeValue;
import shared.locations.HexLocation;
import shared.locations.VertexObject;

public abstract class AIBase extends ServerPlayer {

	protected IAIFacade facade;
	
	
	
	protected AIBase(String name, CatanColor color, int playerID, int playerIndex, IAIFacade facade) {
		super(name, color, playerID, playerIndex);
		this.facade = facade;
	}
	
	
	public abstract HashSet<ResourceType> getPreferredResources();
	public abstract HashSet<AIActionPreference> getPreferredActions();
	
	public void roll() {
		facade.rollNumber(getDiceNumber());
		
	}
	
	public void rob(boolean isSoldier) {
		HexLocation loc = findBestRobbingLocation();
		int bestHand = -1;
		int victimIndex = -1;
		RobPlayerInfo[] robbable = getRobbablePlayers(loc);
		for (RobPlayerInfo info : robbable) {
			if (info.getNumCards() > bestHand) {
				bestHand = info.getNumCards();
				victimIndex = info.getPlayerIndex();
			}
		}
		
		if (isSoldier)
			facade.soldier(victimIndex, loc);
		else
			facade.robPlayer(victimIndex, loc);
	
	}
	
	
	public void discard() {
		ResourceList resources = new ResourceList(); 
		resources.setBrick(getResources().getBrick());
		resources.setOre(getResources().getOre());
		resources.setSheep(getResources().getSheep());
		resources.setWood(getResources().getWood());
		resources.setWheat(getResources().getWheat());
		
		HashSet<ResourceType> preferred = getPreferredResources();
		ResourceList discarding = new ResourceList(0,0,0,0,0);
		int numToDiscard = resources.total() >= 7 ?  resources.total() / 2 : 0;
		if (numToDiscard == 0) return;
		
		if (resources.getBrick() > 0 && !preferred.contains(ResourceType.BRICK)) {
			while (numToDiscard > 0 && resources.getBrick() > 0) {
				discarding.setBrick(discarding.getBrick() + 1);
				resources.setBrick(resources.getBrick() - 1);
				numToDiscard--;
			}	
		}
		if (resources.getWood() > 0 && !preferred.contains(ResourceType.WOOD)) {	
			while (numToDiscard > 0 && resources.getWood() > 0) {
				discarding.setWood(discarding.getWood() + 1);
				resources.setWood(resources.getWood() - 1);
				numToDiscard--;
			}	
		}
		if (resources.getSheep() > 0 && !preferred.contains(ResourceType.SHEEP)) {	
			while (numToDiscard > 0 && resources.getSheep() > 0) {
				discarding.setSheep(discarding.getSheep() + 1);
				resources.setSheep(resources.getSheep() - 1);
				numToDiscard--;
			}	
		}
		if (resources.getOre() > 0 && !preferred.contains(ResourceType.ORE)) {	
			while (numToDiscard > 0 && resources.getOre() > 0) {
				discarding.setOre(discarding.getOre() + 1);
				resources.setOre(resources.getOre() - 1);
				numToDiscard--;
			}	
		}
		if (resources.getWheat() > 0 && !preferred.contains(ResourceType.WHEAT)) {	
			while (numToDiscard > 0 && resources.getWheat() > 0) {
				discarding.setWheat(discarding.getWheat() + 1);
				resources.setWheat(resources.getWheat() - 1);
				numToDiscard--;
			}	
		}
		
		while (numToDiscard > 0 && resources.getBrick() > 0) {
			discarding.setBrick(discarding.getBrick() + 1);
			resources.setBrick(resources.getBrick() - 1);
			numToDiscard--;
		}
		while (numToDiscard > 0 && resources.getWood() > 0) {
			discarding.setWood(discarding.getWood() + 1);
			resources.setWood(resources.getWood() - 1);
			numToDiscard--;
		}
		while (numToDiscard > 0 && resources.getSheep() > 0) {
			discarding.setSheep(discarding.getSheep() + 1);
			resources.setSheep(resources.getSheep() - 1);
			numToDiscard--;
		}
		while (numToDiscard > 0 && resources.getOre() > 0) {
			discarding.setOre(discarding.getOre() + 1);
			resources.setOre(resources.getOre() - 1);
			numToDiscard--;
		}
		while (numToDiscard > 0 && resources.getWheat() > 0) {
			discarding.setWheat(discarding.getWheat() + 1);
			resources.setWheat(resources.getWheat() - 1);
			numToDiscard--;
		}
		
		facade.discardCards(discarding);
		
		
	}
	
	public void play() {
	
		HashSet<AIActionPreference> preferred = getPreferredActions();
		
		ResourceType[] preferredResources =  orderedPreferredResources();
		
		considerMaritimeTrade();
		
		if (preferred.contains(AIActionPreference.DEVCARDS)) {
			if (this.canBuyDevCard()) {
				facade.buyDevCard();
			}
			if (this.canPlayMonopolyCard())
				facade.monopoly(preferredResources[0]);
			if (this.canPlaySoldierCard())
				rob(true);
			if (this.canPlayRoadBuilding())
				playRoadBuilding();
			if (this.canPlayYearOfPlentyCard())
				facade.yearOfPlenty(preferredResources[0],
						preferredResources.length > 1 ? preferredResources[1] : preferredResources[0]);
			
			while (this.canPlayMonumentCard())
				facade.monument();
			
		}
		if (preferred.contains(AIActionPreference.ROADS)) {
			if (this.canBuildRoad()) {
				while (this.canBuildRoad()) {
					if (!placeRoadAI(false))
						break;
				}
			}
		}
		
		if (preferred.contains(AIActionPreference.SETTLEMENTS))
		{
			if (this.canBuildSettlement()) {
				while (this.canBuildSettlement()) {
					if (!placeSettlementAI(false))
						break;
				}
			}
		}
		
		if (preferred.contains(AIActionPreference.CITIES)) {
			if (this.canBuildCity()) {
				while (this.canBuildCity()) {
					if (placeCityAI())
						break;
				}
			}
		}
		
		
		if (this.canBuyDevCard()) {
			facade.buyDevCard();
		}
		if (this.canPlayMonopolyCard())
			facade.monopoly(preferredResources[0]);
		if (this.canPlaySoldierCard())
			rob(true);
		if (this.canPlayRoadBuilding())
			playRoadBuilding();
		if (this.canPlayYearOfPlentyCard())
			facade.yearOfPlenty(preferredResources[0],
					preferredResources.length > 1 ? preferredResources[1] : preferredResources[0]);
		
		while (this.canPlayMonumentCard())
			facade.monument();
		
		
		if (this.canBuildRoad()) {
			while (this.canBuildRoad()) {
				if (!placeRoadAI(false))
					break;
			}
		}
		
		if (this.canBuildSettlement()) {
			while (this.canBuildSettlement()) {
				if (!placeSettlementAI(false))
					break;
			}
		}
		
		if (this.canBuildCity()) {
			while (this.canBuildCity()) {
				if (placeCityAI())
					break;
			}
		}
		
		
		facade.finishTurn();
	}
	
	public void playSetup() {
		
		placeRoadAI(true);
		placeSettlementAI(true);
		
		facade.finishTurn();
	}
	

	private boolean placeRoadAI(boolean free) {
		
		EdgeValue road = findRoadToLayLocation(null, free);
		if (road == null)
			return false;
		
		facade.buildRoad(road.getLocation(), free);
		return true;
	}
	private boolean placeSettlementAI(boolean free) {
		ServerGameMap map = Game.instance().getGameId(0).getServerMap();
		//TODO: fix this
		
		for (VertexObject vertObj : map.getAllPossibleSettlementLocations(getPlayerIndex(), free)) {
			//This isn't very "Smart" but it is something
			facade.buildSettlement(vertObj.getLocation(), free);
			return true;
		}
		
		return false;
	}
	private boolean placeCityAI() {
		ServerGameMap map = Game.instance().getGameId(0).getServerMap();
		//TODO: fix this
		
		for (VertexObject settlement : map.getSettlements()) {
			if (settlement.getOwner() == getPlayerIndex()) {
				facade.buildCity(settlement.getLocation());
				return true;
			}
		}
		
		return false;
	}
	
	private int getDiceNumber() throws IllegalArgumentException{
		return (new Random().nextInt((6 - 1) + 1) + 1) +
			   (new Random().nextInt((6 - 1) + 1) + 1);
		
	}
	
	private void considerMaritimeTrade() {
		HashSet<ResourceType> preferred = getPreferredResources();
		
		
		//TODO: maritime
	}
	
	private void playRoadBuilding() {
		EdgeValue road1 = findRoadToLayLocation(null, false);
		EdgeValue road2 = findRoadToLayLocation(road1, false);
		
		facade.roadBuilding(road1.getLocation(), road2.getLocation());
	}
	
	private HexLocation findBestRobbingLocation() {
		HexLocation bestLocation = null;
		int bestNum = -1;
		ServerGameMap map = Game.instance().getGameId(0).getServerMap();
		//TODO: fix this
		for(Hex hex : map.getHexes()) {
			if (map.canLayRobber(hex.getLocation())) {
				RobPlayerInfo[] robbable = getRobbablePlayers(hex.getLocation());
				if (robbable.length > bestNum) {
					bestNum = robbable.length;
					bestLocation = hex.getLocation();
				}
				
				
			}
		}
		
		if (bestLocation == null)
			System.out.println("WARNING!!! bestLocation is NULL! This shouldn't have happened!");
		
		return bestLocation;
	}
	private RobPlayerInfo[] getRobbablePlayers(HexLocation hexLoc) {
		 boolean[] usedIndices = new boolean[4];
		 List<RobPlayerInfo> info = new ArrayList<RobPlayerInfo>();
		 //TODO: fix this
		 List<VertexObject> municipalities = Game.instance().getGameId(0).getServerMap().getBorderingMunicipalities(hexLoc);
		 for (VertexObject obj : municipalities) {
			 //TODO: fix this
			 Player owner = Game.instance().getGameId(0).getServerPlayers()[obj.getOwner()];
			 if (obj.getOwner() != getPlayerIndex() && owner.getNumOfCards() > 0  && !usedIndices[obj.getOwner()]) {
				 RobPlayerInfo robPlayer = new RobPlayerInfo( owner);
				 info.add(robPlayer);
				 usedIndices[obj.getOwner()] = true;
			 }
		 }
		 ;
		 RobPlayerInfo[] arrayInfo = new RobPlayerInfo[info.size()];
		 for (int n = 0; n < arrayInfo.length; n++) {
			 arrayInfo[n] = info.get(n);
		 }
		 
		 return arrayInfo;
	}
	
	private EdgeValue findRoadToLayLocation(EdgeValue excluding, boolean free) {
		ServerGameMap map = Game.instance().getGameId(0).getServerMap();
		//TODO: fix this
		for (EdgeValue road : map.getAllPossibleRoadLocations(getPlayerIndex(), free)) {
			//This isn't very "Smart" but it is something
			
			if (excluding != road)
				return road;
		}
		return null;
	}
	
	private ResourceType[] orderedPreferredResources() {
		ResourceType[] resources = new ResourceType[Math.min(getPreferredResources().size(), 2)];
		int count = 0;
		for (ResourceType r : getPreferredResources()) {
			resources[count++] = r;
			if (count == 2) break;
		}
		return resources;
	}
}
