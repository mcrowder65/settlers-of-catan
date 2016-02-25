package client.gamestate;

import client.controller.Facade;
import client.data.RobPlayerInfo;
import shared.locations.HexLocation;

public class RobbingState extends GameState {

	public RobbingState(Facade facade){
		super(facade);
	}
	@Override
	public String getGameStatePanelText() {
		return "Choose who to rob";
	}
	@Override
	public boolean isGameStatePanelEnabled() {
		return false;
	}
	@Override
	public boolean placeRobber(HexLocation hexLoc, RobPlayerInfo victim) throws IllegalArgumentException
	{
		if (victim == null || victim.getNumCards() == 0)
			return facade.placeRobber(-1, hexLoc);
		else
			return facade.placeRobber(victim.getPlayerIndex(), hexLoc);
		
	}
	@Override
	public boolean canPlaceRobber(HexLocation hexLoc) {
		return facade.canPlaceRobber(hexLoc);
	}
}
