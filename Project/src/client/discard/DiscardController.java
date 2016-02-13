package client.discard;

import shared.definitions.*;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.gamestate.GameState;
import client.gamestate.IsNotTurnState;
import client.misc.*;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController, Observer {

	private IWaitView waitView;
	private GameState currState;

	/**
	 * DiscardController constructor
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView) {

		super(view);

		this.waitView = waitView;
		this.currState = new IsNotTurnState();
	}

	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}

	public IWaitView getWaitView() {
		return waitView;
	}

	private void setState(String state){

		this.currState =  currState.identifyState(state);
	}

	@Override
	public void increaseAmount(ResourceType resource) {

	}

	@Override
	public void decreaseAmount(ResourceType resource) {

	}

	@Override
	public void discard() {

		getDiscardView().closeModal();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}

