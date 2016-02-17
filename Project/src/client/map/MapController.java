package client.map;

import java.util.*;

import shared.definitions.*;
import shared.locations.*;
import client.base.*;
import client.controller.Facade;
import client.data.*;
import client.gamestate.GameState;
import client.gamestate.IsNotTurnState;


/**
 * Implementation for the map controller
 */
public class MapController extends Controller implements IMapController, Observer {

	private IRobView robView;
	private GameState currState;
	private boolean firstTime;
	public MapController(IMapView view, IRobView robView, Facade facade) {

		super(view);

		setRobView(robView);

		initFromModel();
		this.currState = new IsNotTurnState(facade);
		firstTime = true;
		facade.addObserver(this);
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

	protected void initFromModel() {
//
//		//<temp>
//
//		Random rand = new Random();
//
//		for (int x = 0; x <= 3; ++x) {
//
//			int maxY = 3 - x;			
//			for (int y = -3; y <= maxY; ++y) {				
//				int r = rand.nextInt(HexType.values().length);
//				HexType hexType = HexType.values()[r];
//				HexLocation hexLoc = new HexLocation(x, y);
//				
//				getView().addHex(hexLoc, hexType);
//				/*
//				getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
//						CatanColor.red);
//				//getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
//						CatanColor.blue);
//				//getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
//						CatanColor.orange);
//				//getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.green);
//				//getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.purple);
//				 */
//			}
//
//			if (x != 0) {
//				int minY = x - 3;
//				for (int y = minY; y <= 3; ++y) {
//					int r = rand.nextInt(HexType.values().length);
//					HexType hexType = HexType.values()[r];
//					HexLocation hexLoc = new HexLocation(-x, y);
//					getView().addHex(hexLoc, hexType);
//					/*getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.NorthWest),
//							CatanColor.red);
//					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.SouthWest),
//							CatanColor.blue);
//					getView().placeRoad(new EdgeLocation(hexLoc, EdgeDirection.South),
//							CatanColor.orange);
//					getView().placeSettlement(new VertexLocation(hexLoc,  VertexDirection.NorthWest), CatanColor.green);
//					getView().placeCity(new VertexLocation(hexLoc,  VertexDirection.NorthEast), CatanColor.purple);
//					 */
//				}
//			}
//		}
//
//		PortType portType = PortType.BRICK;
//		getView().addPort(new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North), portType);
//		getView().addPort(new EdgeLocation(new HexLocation(0, -3), EdgeDirection.South), portType);
//		getView().addPort(new EdgeLocation(new HexLocation(-3, 3), EdgeDirection.NorthEast), portType);
//		getView().addPort(new EdgeLocation(new HexLocation(-3, 0), EdgeDirection.SouthEast), portType);
//		getView().addPort(new EdgeLocation(new HexLocation(3, -3), EdgeDirection.SouthWest), portType);
//		getView().addPort(new EdgeLocation(new HexLocation(3, 0), EdgeDirection.NorthWest), portType);
//
//		getView().placeRobber(new HexLocation(0, 0));
//
//		getView().addNumber(new HexLocation(-2, 0), 2);
//		getView().addNumber(new HexLocation(-2, 1), 3);
//		getView().addNumber(new HexLocation(-2, 2), 4);
//		getView().addNumber(new HexLocation(-1, 0), 5);
//		getView().addNumber(new HexLocation(-1, 1), 6);
//		getView().addNumber(new HexLocation(1, -1), 8);
//		getView().addNumber(new HexLocation(1, 0), 9);
//		getView().addNumber(new HexLocation(2, -2), 10);
//		getView().addNumber(new HexLocation(2, -1), 11);
//		getView().addNumber(new HexLocation(2, 0), 12);

		//</temp>
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

	public void placeRoad(EdgeLocation edgeLoc) {
		getView().placeRoad(edgeLoc, CatanColor.orange);
	}

	public void placeSettlement(VertexLocation vertLoc) {
		getView().placeSettlement(vertLoc, CatanColor.orange);
	}

	public void placeCity(VertexLocation vertLoc) {

		getView().placeCity(vertLoc, CatanColor.orange);
	}

	public void placeRobber(HexLocation hexLoc) {

		getView().placeRobber(hexLoc);

		//getRobView().showModal();
	}

	public void startMove(PieceType pieceType, boolean isFree, boolean allowDisconnected) {	

		getView().startDrop(pieceType, CatanColor.orange, true);
	}

	public void cancelMove() {
		//TODO MATT 
	}

	public void playSoldierCard() {	
		currState.playSoldierCard();
	}

	public void playRoadBuildingCard() {	
		currState.playRoadBuildingCard();
	}

	public void robPlayer(RobPlayerInfo victim) {	
		//currState.robPlayer(victim);
	}
	public HexType getHexType(String resource){
		return resource.equals("DESERT") ? HexType.DESERT : resource.equals("WOOD") ? HexType.WOOD : 
				resource.equals("BRICK") ? HexType.BRICK : resource.equals("SHEEP") ? HexType.SHEEP : 
				resource.equals("WHEAT") ? HexType.WHEAT : resource.equals("ORE") ? HexType.ORE : 
				resource.equals("WATER") ? HexType.WATER : null;
	}
	public void initMap(GameMap map){
		firstTime = false;
		Random rand = new Random();
		placePorts(map.getPorts());
		Hex[] hexes = map.getHexes();
		
		for(int i = 0; i < hexes.length; i++){
			
			HexType hexType = hexes[i].getResource().name().equals("NONE") ? HexType.DESERT : 
				getHexType(hexes[i].getResource().name());
			if(hexes[i].getNumber() != 0 && !hexType.equals("DESERT"))
				getView().addNumber(hexes[i].getLocation(), hexes[i].getNumber());
			getView().addHex(hexes[i].getLocation(), hexType);
		}

		
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
	public EdgeDirection getEdgeDirection(String direction){
		//NorthWest, North, NorthEast, SouthEast, South, SouthWest;
		return direction.equals("NorthWest") ? EdgeDirection.NorthWest : direction.equals("North") ? EdgeDirection.North : 
			direction.equals("NorthEast") ? EdgeDirection.NorthEast : direction.equals("SouthEast") ? EdgeDirection.SouthEast : 
				direction.equals("South") ? EdgeDirection.South : direction.equals("SouthWest") ? EdgeDirection.SouthWest :
				null;
	}
	//WOOD, BRICK, SHEEP, WHEAT, ORE, THREE
	public PortType getPortType(String resource){
		return resource.equals("WOOD") ? PortType.WOOD : resource.equals("BRICK") ? PortType.BRICK : 
				resource.equals("SHEEP") ? PortType.SHEEP : resource.equals("WHEAT") ? PortType.WHEAT : 
				resource.equals("ORE") ? PortType.ORE : resource.equals("THREE") ? PortType.THREE :
					null;
	}
	//ResourceType resource, HexLocation location, EdgeDirection direction, int ratio
	public void placePorts(Port[] ports){
		for(int i = 0; i < ports.length; i++){
		//	getView().addPort(new EdgeLocation(ports[i].getLocation(), getEdgeDirection(ports[i].getDirection().name())),  getPortType(ports[i].getResource().name()));
		}
	}
	@Override
	public void update(Observable o, Object arg) {
		
		GameModel model = (GameModel)arg;
		GameMap map = model.getMap();
		
		if(firstTime) 
			initMap(map);
		placeCities(map.getCities());
		setRoads(map.getRoads());
		placeRobber(map.getRobber());
		setSettlements(map.getSettlements());
		
	}
	public void placeCities(VertexObject[] cities){
		for(int i = 0; i < cities.length; i++){
			placeCity(cities[i].getLocation());
		}
	}
	public void setSettlements(VertexObject[] settlements){
		for(int i = 0; i < settlements.length; i++){
			placeSettlement(settlements[i].getLocation());
		}
	}
	public void setRobber(HexLocation robber){
		placeRobber(robber);
	}
	public void setRoads(EdgeValue[] roads){
		for(int i = 0; i < roads.length; i++){
			placeRoad(roads[i].getLocation());
		}
	}
	
}
	
