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

	private void setState(String state){

		this.currState =  currState.identifyState(state);
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

		getRobView().showModal();
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
		currState.robPlayer(victim);
	}

	public void initMap(GameMap map){
		firstTime = false;
		Random rand = new Random();
		
//		for (int x = 0; x <= 3; ++x) {
//			
//			int maxY = 3 - x;			
//			for (int y = -3; y <= maxY; ++y) {				
//				int r = rand.nextInt(HexType.values().length);
//				HexType hexType = HexType.values()[r];
//				HexLocation hexLoc = new HexLocation(x, y);
//				getView().addHex(hexLoc, hexType);
//			}
//
//			if (x != 0) {
//				int minY = x - 3;
//				for (int y = minY; y <= 3; ++y) {
//					int r = rand.nextInt(HexType.values().length);
//					HexType hexType = HexType.values()[r];
//					HexLocation hexLoc = new HexLocation(-x, y);
//					getView().addHex(hexLoc, hexType);
//				}
//			}
//		}
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
		PortType portType = PortType.BRICK;
		getView().addPort(new EdgeLocation(new HexLocation(0, 3), EdgeDirection.North), portType);
		getView().addPort(new EdgeLocation(new HexLocation(0, -3), EdgeDirection.South), portType);
		getView().addPort(new EdgeLocation(new HexLocation(-3, 3), EdgeDirection.NorthEast), portType);
		getView().addPort(new EdgeLocation(new HexLocation(-3, 0), EdgeDirection.SouthEast), portType);
		getView().addPort(new EdgeLocation(new HexLocation(3, -3), EdgeDirection.SouthWest), portType);
		getView().addPort(new EdgeLocation(new HexLocation(3, 0), EdgeDirection.NorthWest), portType);

		getView().placeRobber(new HexLocation(0, 0));
		
		Integer[] nums =  {2,3,3,4,4,5,5,6,6,8,8,9,9,10,10,11,11,12};
		LinkedHashSet<Integer> rands = new LinkedHashSet<Integer>();
		for(int i = 0; i < nums.length; i++){
			int tempSize = rands.size();
			Random randy = new Random();
			do{
				int randNum = randy.nextInt((17 - 0) + 1) + 0;
				rands.add(randNum);
			}while(rands.size() == tempSize);
			
		}
		
		Integer[] randArray = rands.toArray(new Integer[rands.size()]);
		getView().addNumber(new HexLocation(-2, 0), nums[randArray[0]]);
		getView().addNumber(new HexLocation(-2, 1), nums[randArray[1]]);
		getView().addNumber(new HexLocation(-2, 2), nums[randArray[2]]);
		getView().addNumber(new HexLocation(-1, -1), nums[randArray[3]]);
		getView().addNumber(new HexLocation(-1, 0), nums[randArray[4]]);
		getView().addNumber(new HexLocation(-1, 1), nums[randArray[5]]);
		getView().addNumber(new HexLocation(-1, 2), nums[randArray[6]]);
		getView().addNumber(new HexLocation(0, -1), nums[randArray[7]]);
		getView().addNumber(new HexLocation(0, 0), nums[randArray[8]]);
		getView().addNumber(new HexLocation(0, 1), nums[randArray[9]]);
		getView().addNumber(new HexLocation(0, 2), nums[randArray[10]]);
		getView().addNumber(new HexLocation(1, -2), nums[randArray[11]]);
		getView().addNumber(new HexLocation(1, -1), nums[randArray[12]]);
		getView().addNumber(new HexLocation(1, 0), nums[randArray[13]]);
		getView().addNumber(new HexLocation(1, 1), nums[randArray[14]]);
		getView().addNumber(new HexLocation(2, -2), nums[randArray[15]]);
		getView().addNumber(new HexLocation(2, -1), nums[randArray[16]]);
		getView().addNumber(new HexLocation(2, 0), nums[randArray[17]]);
		HexType[] hexTypes = {
				HexType.WOOD,
				HexType.WOOD,
				HexType.WOOD,
				HexType.WOOD,
				HexType.WHEAT,
				HexType.WHEAT,
				HexType.WHEAT,
				HexType.WHEAT,
				HexType.BRICK,
				HexType.BRICK,
				HexType.BRICK,
				HexType.SHEEP,
				HexType.SHEEP,
				HexType.SHEEP,
				HexType.SHEEP,
				HexType.ORE,
				HexType.ORE,
				HexType.ORE,
				HexType.DESERT
			};
		rands = new LinkedHashSet<Integer>();
		for(int i = 0; i < hexTypes.length; i++){
			int tempSize = rands.size();
			Random randy = new Random();
			do{
				int randNum = randy.nextInt((18 - 0) + 1) + 0;
				rands.add(randNum);
			}while(rands.size() == tempSize);
		}
		randArray = rands.toArray(new Integer[rands.size()]);
		getView().addHex(new HexLocation(-2, 0), hexTypes[randArray[0]]);
		getView().addHex(new HexLocation(-2, 1), hexTypes[randArray[1]]);
		getView().addHex(new HexLocation(-2, 2), hexTypes[randArray[2]]);
		getView().addHex(new HexLocation(-1, -1), hexTypes[randArray[3]]);
		getView().addHex(new HexLocation(-1, 0), hexTypes[randArray[4]]);
		getView().addHex(new HexLocation(-1, 1), hexTypes[randArray[5]]);
		getView().addHex(new HexLocation(-1, 2), hexTypes[randArray[6]]);
		getView().addHex(new HexLocation(0, -1), hexTypes[randArray[7]]);
		getView().addHex(new HexLocation(0, 0), hexTypes[randArray[8]]);
		getView().addHex(new HexLocation(0, 1), hexTypes[randArray[9]]);
		getView().addHex(new HexLocation(0, 2), hexTypes[randArray[10]]);
		getView().addHex(new HexLocation(1, -2), hexTypes[randArray[11]]);
		getView().addHex(new HexLocation(1, -1), hexTypes[randArray[12]]);
		getView().addHex(new HexLocation(1, 0), hexTypes[randArray[13]]);
		getView().addHex(new HexLocation(1, 1), hexTypes[randArray[14]]);
		getView().addHex(new HexLocation(2, -2), hexTypes[randArray[15]]);
		getView().addHex(new HexLocation(2, -1), hexTypes[randArray[16]]);
		getView().addHex(new HexLocation(2, 0), hexTypes[randArray[17]]);
		getView().addHex(new HexLocation(0,-2), hexTypes[randArray[18]]);
		//getView().addHex(hexLoc, hexType);
	}
	@Override
	public void update(Observable o, Object arg) {
		//TODO implement functionality to either create a random game or upload an existing game.
		GameModel model = (GameModel)arg;
		GameMap map = model.getMap();
		
		if(firstTime) 
			initMap(map);
		placeCities(map.getCities());
		setRoads(map.getRoads());
		//placeRobber(map.getRobber());
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

