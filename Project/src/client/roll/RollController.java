package client.roll;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.gamestate.GameState;
import client.gamestate.IsNotTurnState;


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
	public RollController(IRollView view, IRollResultView resultView) {

		super(view);
		
		setResultView(resultView);
		this.currState = new IsNotTurnState();
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
	
	@Override
	public void rollDice() {

		getResultView().showModal();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

}

