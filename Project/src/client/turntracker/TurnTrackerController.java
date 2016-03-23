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

		this.currState = new IsNotTurnState(facade);
		this.facade = facade;
		
	}

	

	@Override
	public ITurnTrackerView getView() {

		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() {
		synchronized (DataUtils.modelLock) {
			boolean success = currState.finishTurn();
			if (success) {
				GameModel model = currState.fetchModel();
				currState = currState.identifyState(model.getTurnTracker());
				getView().updateGameState(currState.getGameStatePanelText(), currState.isGameStatePanelEnabled());
			
			}
		}
	}
	/**
	 * This method initializes the turn tracker controller to the players and colors 
	 * @param model
	 */
	private void startInit(GameModel model){
		//This should only happen once, at the beginning of the first
		//round. Once this method is completed, the boolean is set up to true
		//to prevent further access to this method
		
		for(Player player : model.getPlayers()) {
			getView().initializePlayer(player.getPlayerIndex(), player.getName(), player.getColor());
			
		}
		
		setUpDone = true;
	}

	/**
	 * This method keeps the victory points, and awards (largest army and longest road) updated.
	 * It runs every time there's an update on the server. 
	 * @param model
	 */
	private void initFromModel(GameModel model) {
		getView().setLocalPlayerColor(model.getLocalPlayer(facade.getPlayerId()).getColor());
		
		
		for(Player player : model.getPlayers()) {
			getView().initializePlayer(player.getPlayerIndex(), player.getName(), player.getColor());
			int playerIndex = player.getPlayerIndex();
			int points = player.getVictoryPoints();
			
			boolean highlight;
			if(model.getTurnTracker().getCurrentTurn() != player.getPlayerIndex()) highlight = false;
			else highlight = true;
			
			boolean largestArmy = false;
			if(playerIndex == model.getTurnTracker().getLargestArmy()) largestArmy = true;
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
		
		//If statement is for initialization, and else for the constant update of the turn tracker.  
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
		getView().reset();
		setUpDone = false;
		
		
	}

}

