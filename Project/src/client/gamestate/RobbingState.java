package client.gamestate;

import client.controller.Facade;

public class RobbingState extends GameState {

	public RobbingState(Facade facade){
		super(facade);
	}
	@Override
	public String getGameStatePanelText() {
		return "Choose who to rob";
	}
}
