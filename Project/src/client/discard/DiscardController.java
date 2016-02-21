package client.discard;

import shared.definitions.*;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.controller.Facade;
import client.data.GameManager;
import client.gamestate.DiscardingState;
import client.gamestate.GameState;
import client.gamestate.IsNotTurnState;
import client.misc.*;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController, Observer {

	private IWaitView waitView;
	private GameState currState;
	private int neededToDiscard;
	private int currentAmountToDiscard;

	/**
	 * DiscardController constructor
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView, Facade facade) {

		super(view);

		this.waitView = waitView;
		this.currState = new IsNotTurnState(facade);
	
	}

	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}

	public IWaitView getWaitView() {
		return waitView;
	}

	

	@Override
	public void increaseAmount(ResourceType resource) {
		currentAmountToDiscard++;
		getDiscardView().setStateMessage(currentAmountToDiscard + "/" + neededToDiscard);
		getDiscardView().setDiscardButtonEnabled(currentAmountToDiscard == neededToDiscard);
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		currentAmountToDiscard--;
		getDiscardView().setStateMessage(currentAmountToDiscard + "/" + neededToDiscard);
		getDiscardView().setDiscardButtonEnabled(currentAmountToDiscard == neededToDiscard);
	
	}
	
	public void enterGame() {
		currState.addObserver(this);
	}
	public void leaveGame() {
		currState.deleteObserver(this);
	}

	@Override
	public void discard() {

		getDiscardView().closeModal();
	}

	@Override
	public void update(Observable o, Object arg) {
		GameModel model = (GameModel)arg;
		currState = currState.identifyState(model.getTurnTracker());
		IDiscardView view = getDiscardView();
		if (currState instanceof DiscardingState && !view.isModalShowing()) {
			
			ResourceList myResources = currState.getPlayerResources();
			view.setResourceMaxAmount(ResourceType.WOOD, myResources.getWood());
			view.setResourceMaxAmount(ResourceType.BRICK, myResources.getBrick());
			view.setResourceMaxAmount(ResourceType.ORE, myResources.getOre());
			view.setResourceMaxAmount(ResourceType.SHEEP, myResources.getSheep());
			view.setResourceMaxAmount(ResourceType.WHEAT, myResources.getWheat());
			
			
			view.showModal();
		}
	}

}

