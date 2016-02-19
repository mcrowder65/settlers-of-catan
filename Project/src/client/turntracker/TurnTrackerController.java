package client.turntracker;

import shared.definitions.CatanColor;
import shared.definitions.GameModel;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.controller.Facade;
import client.data.GameManager;
import client.gamestate.GameState;
import client.gamestate.IsNotTurnState;
import client.utils.DataUtils;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController, Observer {

	private GameState currState;
	private Facade facade;

	public TurnTrackerController(ITurnTrackerView view, Facade facade) {

		super(view);

		initFromModel();
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

	private void initFromModel() {
		//<temp>
		getView().setLocalPlayerColor(CatanColor.orange);
		
		//</temp>
	}

	@Override
	public void update(Observable o, Object arg) {
		GameModel model = (GameModel)arg;
		currState = currState.identifyState(model.getTurnTracker());
		if ( DataUtils.countNumPlayers(model.getPlayers())  == 4) {
			getView().updateGameState(currState.getGameStatePanelText(), currState.isGameStatePanelEnabled()); 
		}
		
		//initFromModel

	}
	

	public void enterGame() {
		this.facade.addObserver(this);
		
	}
	public void leaveGame() {
		this.facade.deleteObserver(this);
		
	}

}

