package client.map;

import java.util.*;

import shared.definitions.*;
import shared.locations.*;
import client.base.*;
import client.controller.Facade;
import client.data.*;
import client.gamestate.*;
import client.roll.IRollResultView;
import client.utils.DataUtils;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController, Observer {

	private IRobView robView;
	private IRollResultView rollResultView;
	private GameState currState;
	private boolean firstTime;
	private HexLocation movedRobberLocation;
	private boolean isPlayingSoldier = false;
	private int roadBuildingPassNum = -1;
	private EdgeLocation roadBuildingFirstPassLocation = null;
	private boolean disableUpdates = false;
	

	public MapController(IMapView view, IRobView robView,IRollResultView rollResultView, Facade facade) {

		super(view);

		setRobView(robView);

		this.rollResultView = rollResultView;
		this.currState = new IsNotTurnState(facade);
		firstTime = true;
		
	}


	public IMapView getView() {

		return (IMapView)super.getView();
	}

	private IRobView getRobView() {
		return robView;
	}
	private void setRobView(IRobView robView) {
		this.robView = robView;
	}
	public boolean canPlaceRoad(EdgeLocation edgeLoc) {
		return currState.canPlaceRoad(edgeLoc);
	}

	public boolean canPlaceSettlement(VertexLocation vertLoc) {
		return currState.canPlaceSettlement(vertLoc);
	}

	public boolean canPlaceCity(VertexLocation vertLoc) {
		return currState.canPlaceCity(vertLoc);
	}

	public boolean canPlaceRobber(HexLocation hexLoc) {
		return currState.canPlaceRobber(hexLoc);
	}
	/*
	public boolean isRoadInModel(EdgeLocation edgeLoc){
		GameMap map = currState.fetchModel().getMap();
		EdgeValue temp = new EdgeValue(currState.getPlayerId(), edgeLoc);
		EdgeValue[] roads = map.getRoads();
		for(int i = 0; i < roads.length; i++){
			if(roads[i].getLocation().equals(temp.getLocation()))
				return true;
		}
		return false;
	}*/
	public void placeRoad(EdgeLocation edgeLoc) {
		synchronized(DataUtils.modelLock) {
			
			switch (roadBuildingPassNum) {
			case 0: //First road of RoadBuilding devcard
				roadBuildingFirstPassLocation = edgeLoc;
				currState.placeRoadLocally(edgeLoc);
				placeRoadVisual(edgeLoc, currState.getPlayerColor());
				getView().startDrop(PieceType.ROAD, currState.getPlayerColor(), false);
				roadBuildingPassNum = 1;
				disableUpdates = true;//Need to do this or something like it
				                      //in order to prevent the update function
				                      //from overwriting the placed temp road
				break;
			case 1: //Second road of RoadBuilding devcard
				if (currState.playRoadBuildingCard(roadBuildingFirstPassLocation, edgeLoc))
				{
					currState.cancelMove();
					placeRoadVisual(edgeLoc, currState.getPlayerColor());
					roadBuildingPassNum = -1;
					roadBuildingFirstPassLocation = null;
					disableUpdates = false;
				}
				break;
			default: //Not a devcard, just a regular road
				boolean success = currState.buildRoad(edgeLoc);
				if (success) {
					placeRoadVisual(edgeLoc, currState.getPlayerColor());
					GameModel model = null;
					
						model = currState.fetchModel();
					
						currState = currState.identifyState(model.getTurnTracker());
						if (currState instanceof FirstRoundState ||
							currState instanceof SecondRoundState) {
								getView().startDrop(PieceType.SETTLEMENT, currState.getPlayerColor(), false);
								
							
							}
						else {
							
						}
					
				} else {
					System.out.println("WARNING! placeRoad in MapController returned a fail.");
				}
				
				
				break;
			}
			
			
		
		}
		
	}
	public void placeRoadVisual(EdgeLocation edgeLoc, CatanColor color) {
		getView().placeRoad(edgeLoc, color);
	}
	/*
	public boolean isSettlementInModel(VertexLocation vertLoc){
		GameMap map = currState.fetchModel().getMap();
		VertexLocation tempVL = new VertexLocation(vertLoc.getHexLoc(), vertLoc.getDir());
		VertexObject[] settlements = map.getSettlements();
		for(int i = 0; i < settlements.length; i++){
			if(settlements[i].getLocation().equals(tempVL))
				return true;
		}
		
		return false;
	}*/
	public void placeSettlement(VertexLocation vertLoc) {
		/*if(isSettlementInModel(vertLoc))
			getView().placeSettlement(vertLoc, color);
		else*/
		synchronized(DataUtils.modelLock) {
			boolean success = currState.buildSettlement(vertLoc);
			if (success) {
				placeSettlementVisual(vertLoc, currState.getPlayerColor());
				GameModel model = null;
				
					model = currState.fetchModel();
				
					currState = currState.identifyState(model.getTurnTracker());
					if (currState instanceof FirstRoundState ||
						currState instanceof SecondRoundState) {
							currState.finishTurn();
						
						}
				
			}
			else
				System.out.println("WARNING! placeSettlement in MapController failed.");
		}
	}
	public void placeSettlementVisual(VertexLocation vertLoc, CatanColor color) {
		getView().placeSettlement(vertLoc, color);
	}
	/*
	public boolean isCityInModel(VertexLocation vertLoc){
		GameMap map = currState.fetchModel().getMap();
		VertexLocation tempVL = new VertexLocation(vertLoc.getHexLoc(), vertLoc.getDir());
		VertexObject[] cities = map.getCities();
		for(int i = 0; i < cities.length; i++){
			if(cities[i].getLocation().equals(tempVL))
				return true;
		}
		return false;
	}*/
	public void placeCity(VertexLocation vertLoc) {
		/*if(isCityInModel(vertLoc))
			getView().placeCity(vertLoc, color);
		else*/
		boolean success = currState.buildCity(vertLoc);
		if (success)
			placeCityVisual(vertLoc, currState.getPlayerColor());
		else
			System.out.println("WARNING! placeCity in MapController failed.");
	}
	public void placeCityVisual(VertexLocation vertLoc, CatanColor color) {
		getView().placeCity(vertLoc, color);
	}
	
	public void startRobber() {
	    getView().startDrop(PieceType.ROBBER, CatanColor.red, false);
	}
	
	public void placeRobber(HexLocation hexLoc) {
		placeRobberVisual(hexLoc);
		movedRobberLocation = hexLoc;
		robView.setPlayers(currState.getRobbablePlayers(hexLoc));
		
		robView.showModal();
		
	}
	public void placeRobberVisual(HexLocation hexLoc) {
		getView().placeRobber(hexLoc);
	}
	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	
		
		getView().startDrop(pieceType, currState.getPlayerColor(), true);
	}

	public void cancelMove() {
		currState.cancelMove();
	}

	public void playSoldierCard() {	
		isPlayingSoldier = true;
		startRobber();
	}

	public void playRoadBuildingCard() {	
		roadBuildingPassNum = 0;
		getView().startDrop(PieceType.ROAD, currState.getPlayerColor(), false);
	}

	public void robPlayer(RobPlayerInfo victim) {	
		if (movedRobberLocation == null) 
			System.out.println("WARNING! movedRobberLocation in robPlayer was not set.");
		else {
			
			
		
			if (isPlayingSoldier)
				currState.playSoldierCard(movedRobberLocation, victim);
			else
				currState.placeRobber(movedRobberLocation, victim);
			
			isPlayingSoldier = false;
		}
	}
	public HexType getHexType(String resource){
		return resource.equals("DESERT") ? HexType.DESERT : resource.equals("WOOD") ? HexType.WOOD : 
				resource.equals("BRICK") ? HexType.BRICK : resource.equals("SHEEP") ? HexType.SHEEP : 
				resource.equals("WHEAT") ? HexType.WHEAT : resource.equals("ORE") ? HexType.ORE : 
				resource.equals("WATER") ? HexType.WATER : null;
	}
	public void placeHexes(Hex[] hexes){
		for(int i = 0; i < hexes.length; i++){
			HexType hexType = hexes[i].getResource().name().equals("NONE") ? HexType.DESERT : 
				getHexType(hexes[i].getResource().name());
			if(hexes[i].getNumber() != 0)
				getView().addNumber(hexes[i].getLocation(), hexes[i].getNumber());
			getView().addHex(hexes[i].getLocation(), hexType);
		}
	}
	public void placeWater(){
		HexLocation[] water = {
				new HexLocation(-3,0), 
				new HexLocation(-3,1), 
				new HexLocation(-3,2), 
				new HexLocation(-3,3), 
				new HexLocation(-2,3), 
				new HexLocation(-1,3), 
				new HexLocation(0,3), 
				new HexLocation(1,2), 
				new HexLocation(2,1), 
				new HexLocation(3,0), 
				new HexLocation(3,-1), 
				new HexLocation(3,-2), 
				new HexLocation(3,-3), 
				new HexLocation(2,-3), 
				new HexLocation(1,-3), 
				new HexLocation(0,-3), 
				new HexLocation(-1,-2), 
				new HexLocation(-2,-1)
		};
		for(int i = 0; i < water.length; i++){
			getView().addHex(water[i], HexType.WATER);
		}	
	}
	public void initMap(GameMap map){
		firstTime = false;
		placeHexes(map.getHexes());
		placeWater();
		placePorts(map.getPorts());
	}
	public EdgeDirection getEdgeDirection(String direction){
		return direction.equals("NorthWest") ? EdgeDirection.NorthWest : direction.equals("North") ? EdgeDirection.North : 
			direction.equals("NorthEast") ? EdgeDirection.NorthEast : direction.equals("SouthEast") ? EdgeDirection.SouthEast : 
				direction.equals("South") ? EdgeDirection.South : direction.equals("SouthWest") ? EdgeDirection.SouthWest :
				null;
	}
	public PortType getPortType(String resource){
		return resource.equals("WOOD") ? PortType.WOOD : resource.equals("BRICK") ? PortType.BRICK : 
				resource.equals("SHEEP") ? PortType.SHEEP : resource.equals("WHEAT") ? PortType.WHEAT : 
				resource.equals("ORE") ? PortType.ORE : resource.equals("THREE") ? PortType.THREE :
				resource.equals("NONE") ? PortType.THREE :
					null;
	}
	public void placePorts(Port[] ports){
		for(int i = 0; i < ports.length; i++){
			getView().addPort(new EdgeLocation(ports[i].getLocation(), getEdgeDirection(ports[i].getDirection().name())),  getPortType(ports[i].getResource().name()));
		}
	}
	@Override
	public void update(Observable o, Object arg) {
		
		if (disableUpdates) return;
		
		GameModel model = (GameModel)arg;
		GameMap map = model.getMap();
		
		currState = currState.identifyState(model.getTurnTracker());
		placeRobberVisual(map.getRobber());
		if(firstTime) 
			initMap(map);
		
		setCities(map.getCities(), model.getPlayers());
		setRoads(map.getRoads(), model.getPlayers());
		
		setSettlements(map.getSettlements(), model.getPlayers());
		
		if ( (currState instanceof FirstRoundState || currState instanceof SecondRoundState) 
				&&  !getView().isOverlayShowing()) {
			getView().startDrop(PieceType.ROAD, currState.getPlayerColor(), false);
			
			
		} else if (currState instanceof RobbingState 
				&& !getView().isOverlayShowing() 
				&& !getRobView().isModalShowing() 
				&& !getRollResultView().isModalShowing()) {
			
			startRobber(); 
		}
		
		
	}
	public IRollResultView getRollResultView() {
		return rollResultView;
	}


	public void setCities(VertexObject[] cities, Player[] players){
		for(int i = 0; i < cities.length; i++){
			VertexObject city = cities[i];
			CatanColor color = players[city.getOwner()].getColor();
			if (color == null)
				System.out.println("WARNING! Color in setCities is null");
			
			placeCityVisual(cities[i].getLocation(), color);
		}
	}
	public void setSettlements(VertexObject[] settlements, Player[] players){
		for(int i = 0; i < settlements.length; i++){
			VertexObject settlement = settlements[i];
			CatanColor color = players[settlement.getOwner()].getColor();
			if (color == null)
				System.out.println("WARNING! Color in setSettlements is null");
			
			placeSettlementVisual(settlements[i].getLocation(), color);
		}
	}
	
	public void setRoads(EdgeValue[] roads, Player[] players){
		for(int i = 0; i < roads.length; i++){
			EdgeValue road = roads[i];
			CatanColor color = players[road.getOwner()].getColor();
			if (color == null)
				System.out.println("WARNING! Color in setSettlements is null");
			
			placeRoadVisual(roads[i].getLocation(), color);
		}
	}
	
	public void enterGame() {
		
		GameModel model = null;
		synchronized(DataUtils.modelLock) {
			model = currState.fetchModel();
		}
		
		currState = currState.identifyState(model.getTurnTracker());
		if (currState instanceof FirstRoundState ||
		    currState instanceof SecondRoundState) {
				
				getView().startDrop(PieceType.ROAD, currState.getPlayerColor(), false);
				//getView().startDrop(PieceType.SETTLEMENT, model.getLocalPlayer(currState.getPlayerId()).getColor(), true);
				
			}
		
		currState.addObserver(this);
		
	}
	public void leaveGame() {
		currState.deleteObserver(this);
		getView().resetAll();
		firstTime = true;
	}
}