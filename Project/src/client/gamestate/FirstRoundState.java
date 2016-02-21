package client.gamestate;

import client.controller.Facade;
import shared.locations.EdgeLocation;
import shared.locations.VertexLocation;

public class FirstRoundState extends GameState {
	
	public FirstRoundState(Facade facade){
		super(facade);
	}
	
	@Override
	public boolean canPlaceRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{
		return facade.canBuildRoad(edgeLoc);
	}

	@Override
	public boolean canPlaceSettlement(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.canBuildSettlement(vertLoc);
	}
	
	@Override
	public boolean buildRoad(EdgeLocation edgeLoc) throws IllegalArgumentException{
		return facade.buildRoad(edgeLoc,true);
	}
	
	@Override
	public boolean buildSettlement(VertexLocation vertLoc) throws IllegalArgumentException{
		return facade.buildSettlement(vertLoc,true);
	}

	@Override
	public boolean finishTurn() {
		return facade.finishTurn();
	}
	@Override
	public String getGameStatePanelText() {
		return "Place Initial Pieces";
	}
	@Override
	public boolean isGameStatePanelEnabled() {
		return false;
	}
	
}
