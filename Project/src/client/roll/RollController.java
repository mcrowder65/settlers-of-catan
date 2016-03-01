package client.roll;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.controller.Facade;
import client.data.GameManager;
import client.gamestate.GameState;
import client.gamestate.IsNotTurnState;
import client.gamestate.RollingState;
import shared.definitions.GameModel;


/**
 * Implementation for the roll controller
 */
public class RollController extends Controller implements IRollController,Observer {

	private IRollResultView resultView;
	private GameState currState;
	/**
	 * RollController constructor
	 * 
	 * @param view Roll view
	 * @param resultView Roll result view
	 */
	public RollController(IRollView view, IRollResultView resultView, Facade facade) {

		super(view);

		setResultView(resultView);
		this.currState = new IsNotTurnState(facade);
		facade.addObserver(this);
	}

	public IRollResultView getResultView() {
		return resultView;
	}
	public void setResultView(IRollResultView resultView) {
		this.resultView = resultView;
	}

	public IRollView getRollView() {
		return (IRollView)getView();
	}

	
	/**
	 * rolls the dice and sets the results in the view
	 * 
	 */
	@Override
	public void rollDice() {
		int result = currState.rollNumber(); //calls the current state which will call the facade to roll the number
		this.getResultView().setRollValue(result); //sets the results in the modal
		getResultView().showModal();
	}

	@Override
	public void update(Observable o, Object arg) {
		GameModel model = (GameModel)arg;
		currState = currState.identifyState(model.getTurnTracker());
		
		if ( (currState instanceof RollingState) && !getRollView().isModalShowing() && !getResultView().isModalShowing()) {
	
			getRollView().showModal();
		}

	}

}

