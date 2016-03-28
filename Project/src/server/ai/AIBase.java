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
import shared.definitions.Port;
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
		int numToDiscard = resources.total() > 7 ?  resources.total() / 2 : 0;
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
		
	int roadLimit = 0;
		

		if (preferred.contains(AIActionPreference.SETTLEMENTS))
		{
			while (this.canBuildSettlement()) {
					if (!placeSettlementAI(false))
						break;
					
				
				}
			
		}
		
		roadLimit = 0;
		if (preferred.contains(AIActionPreference.ROADS)) {
			while (this.canBuildRoad()) {
					if (!placeRoadAI(false))
						break;
					roadLimit++;
					if (roadLimit == 1 && this.canBuildSettlement())
						break;
					if (roadLimit == 2)
						break;
				}
			
		}
		
		
		
		if (preferred.contains(AIActionPreference.CITIES)) {
			while (this.canBuildCity()) {
					if (!placeCityAI())
						break;
				}
			
		}
		
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
		
		
		while (this.canBuildSettlement()) {
			if (!placeSettlementAI(false))
				break;
	    }
		

		roadLimit = 0;
		while (this.canBuildRoad()) {
				if (!placeRoadAI(false))
					break;
				roadLimit++;
				if (roadLimit == 1 && this.canBuildSettlement())
					break;
				if (roadLimit == 2)
					break;
			}

	
	
		
		while (this.canBuildCity()) {
				if (!placeCityAI())
					break;
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
		ServerGameMap map = Game.instance().getGameId(facade.getGameId()).getServerMap();
		
	    VertexObject bestLocation = null;
	    int bestVal = -1;
		
		for (VertexObject vertObj : map.getAllPossibleSettlementLocations(getPlayerIndex(), free)) {
			
			int heuristic = getHeuristicVal(vertObj, map);
			if (heuristic > bestVal) {
				bestVal = heuristic;
				bestLocation = vertObj;
			}
			
		}
		if (bestLocation == null)
			return false;
		
		return facade.buildSettlement(bestLocation.getLocation(), free);
	}
	
	private int getHeuristicVal(VertexObject obj, ServerGameMap map) {
		List<Hex> surrounding = map.getSurroundingHexes(obj.getLocation());
		HashSet<ResourceType> preferredResources =  getPreferredResources();
		int total = 0;
		for (Hex hex : surrounding) {
			if (hex.getResource() == null) continue;
			
			if (preferredResources.contains(hex.getResource())) {
			     total += 2;
			}
			total += 6 - Math.abs( 7 - hex.getNumber());
		}
		
		return total;
	}
	private boolean placeCityAI() {
		ServerGameMap map = Game.instance().getGameId(facade.getGameId()).getServerMap();
		
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
		HashSet<ResourceType> preferredResources =  getPreferredResources();
		ResourceType lowestPreferred = null;
		int low = 9999;
		for (ResourceType pref : preferredResources) {
			if (this.getResources().getResource(pref) < low) {
				low = this.getResources().getResource(pref);
				lowestPreferred = pref;
			}
		}
		HashSet<ResourceType> lowPriorityResources = new HashSet<ResourceType>();
		if (!preferredResources.contains(ResourceType.BRICK)) lowPriorityResources.add(ResourceType.BRICK);
		if (!preferredResources.contains(ResourceType.ORE)) lowPriorityResources.add(ResourceType.ORE);
		if (!preferredResources.contains(ResourceType.WHEAT)) lowPriorityResources.add(ResourceType.WHEAT);
		if (!preferredResources.contains(ResourceType.SHEEP)) lowPriorityResources.add(ResourceType.SHEEP);
		if (!preferredResources.contains(ResourceType.WOOD)) lowPriorityResources.add(ResourceType.WOOD);
		
	ServerGameMap map = Game.instance().getGameId(facade.getGameId()).getServerMap();
		for (ResourceType lowPrio : lowPriorityResources) {
			if (this.getResources().getResource(lowPrio) >= 2 && checkPorts(map, lowPrio, 2)) {
				facade.maritimeTrade(2, lowPrio, lowestPreferred); 
			} else if (this.getResources().getResource(lowPrio) >= 3 && checkPorts(map, lowPrio, 3)) {
				facade.maritimeTrade(3, lowPrio, lowestPreferred); 
			} else if (this.getResources().getResource(lowPrio) >= 4) {
				facade.maritimeTrade(4, lowPrio, lowestPreferred); 
			}
		}
	
		
	}
	
	private void playRoadBuilding() {
		EdgeValue road1 = findRoadToLayLocation(null, false);
		EdgeValue road2 = findRoadToLayLocation(road1, false);
		
		facade.roadBuilding(road1.getLocation(), road2.getLocation());
	}
	
	private HexLocation findBestRobbingLocation() {
		HexLocation bestLocation = null;
		int bestNum = -1;
		ServerGameMap map = Game.instance().getGameId(facade.getGameId()).getServerMap();
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
		 List<VertexObject> municipalities = Game.instance().getGameId(facade.getGameId()).getServerMap().getBorderingMunicipalities(hexLoc);
		 for (VertexObject obj : municipalities) {
			 Player owner = Game.instance().getGameId(facade.getGameId()).getServerPlayers()[obj.getOwner()];
			 if (obj.getOwner() != getPlayerIndex() && owner.getNumOfCards() > 0  && !usedIndices[obj.getOwner()]) {
				 RobPlayerInfo robPlayer = new RobPlayerInfo( owner);
				 info.add(robPlayer);
				 usedIndices[obj.getOwner()] = true;
			 }
		 }
		 
		 RobPlayerInfo[] arrayInfo = new RobPlayerInfo[info.size()];
		 for (int n = 0; n < arrayInfo.length; n++) {
			 arrayInfo[n] = info.get(n);
		 }
		 
		 return arrayInfo;
	}
	
	private EdgeValue findRoadToLayLocation(EdgeValue excluding, boolean free) {
		ServerGameMap map = Game.instance().getGameId(facade.getGameId()).getServerMap();
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
	
	public boolean checkPorts(ServerGameMap map, ResourceType input, int ratio){
		List<Port> ports = map.getPersonalPorts(this.getPlayerIndex());
		boolean correct = false;
		if(ratio == 3){
			for(int i=0; i<ports.size(); i++){
				Port port = ports.get(i);
				if(port.getRatio() == ratio){
					correct = true;
				}
			}
		}
		else if(ratio == 2){
			for(int i=0; i<ports.size(); i++){
				Port port = ports.get(i);
				if(port.getRatio() == ratio && port.getResource() == input){
					correct = true;
				}
			}
		}
		
		return correct;
	}
	
	
}
