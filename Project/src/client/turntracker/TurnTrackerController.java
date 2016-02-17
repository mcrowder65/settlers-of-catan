package client.turntracker;

import shared.definitions.CatanColor;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.controller.Facade;
import client.data.GameManager;
import client.gamestate.GameState;
import client.gamestate.IsNotTurnState;


/**
 * Implementation for the turn tracker controller
 */
public class TurnTrackerController extends Controller implements ITurnTrackerController, Observer {

	private GameState currState;

	public TurnTrackerController(ITurnTrackerView view, Facade facade) {

		super(view);

		initFromModel();
		this.currState = new IsNotTurnState(facade);
		facade.addObserver(this);
	}

	private void setState(String state){

		this.currState =  currState.identifyState(state);
	}

	@Override
	public ITurnTrackerView getView() {

		return (ITurnTrackerView)super.getView();
	}

	@Override
	public void endTurn() {

	}

	private void initFromModel() {
		//<temp>
		getView().setLocalPlayerColor(CatanColor.red);
		//</temp>
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}

