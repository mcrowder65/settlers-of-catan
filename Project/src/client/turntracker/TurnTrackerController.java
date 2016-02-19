package client.turntracker;

import shared.definitions.CatanColor;
import shared.definitions.GameModel;
import shared.definitions.Player;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.controller.Facade;
import client.data.GameManager;
import client.gamestate.FirstRoundState;
import client.gamestate.GameState;
import client.gamestate.IsNotTurnState;
import client.utils.DataUtils;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController, Observer {

	private GameState currState;
	private Facade facade;
	
	private boolean setUpDone = false;

	public TurnTrackerController(ITurnTrackerView view, Facade facade) {

		super(view);

		//initFromModel();
		this.currState = new IsNotTurnState(facade);
		this.facade = facade;
		
	}

	

	@Override
	public ITurnTrackerView getView() {

		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() {
		currState.finishTurn();
	}
	
	private void startInit(GameModel model){
		//This should only happen once, at the beginning of the first
		//round. Once this method is completed, the boolean is set up to true
		//to prevent further access to this method
		
		for(Player player : model.getPlayers()) {
			getView().initializePlayer(player.getPlayerIndex(), player.getName(), player.getColor());
			
		}
		//Player player = model.getLocalPlayer(facade.getPlayerId());
		//getView().initializePlayer(player.getPlayerIndex(), player.getName(), player.getColor());
		
		setUpDone = true;
	}

	private void initFromModel(GameModel model) {
		getView().setLocalPlayerColor(model.getLocalPlayer(facade.getPlayerId()).getColor());
		
		for(Player player : model.getPlayers()) {
			//Player player = model.getLocalPlayer();
			
			
			int playerIndex = player.getPlayerIndex();
			int points = player.getVictoryPoints();
			
			boolean highlight;
			if(model.getTurnTracker().getCurrentTurn() != player.getPlayerIndex()) highlight = false;
			else highlight = true;
			
			boolean largestArmy = false;
			if(playerIndex == model.getTurnTracker().getlargestArmy()) largestArmy = true;
			else largestArmy = false;
			
			boolean longestRoad = false;
			if(playerIndex == model.getTurnTracker().getLongestRoad()) longestRoad = true;
			else longestRoad = false;
			
			getView().updatePlayer(playerIndex, points, highlight, largestArmy, longestRoad);
		
		}
			
		
		

	}

	@Override
	public void update(Observable o, Object arg) {
		GameModel model = (GameModel)arg;
		currState = currState.identifyState(model.getTurnTracker());
		if ( DataUtils.countNumPlayers(model.getPlayers())  == 4) {
			getView().updateGameState(currState.getGameStatePanelText(), currState.isGameStatePanelEnabled()); 
		}
		
		if(setUpDone == false) {
			startInit(model);
		}
		else{
			initFromModel(model);
		}
		
	}
	

	public void enterGame() {
		this.facade.addObserver(this);
		
	}
	public void leaveGame() {
		this.facade.deleteObserver(this);
		
	}

}

