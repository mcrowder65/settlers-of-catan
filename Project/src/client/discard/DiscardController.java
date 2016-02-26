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
import client.roll.IRollResultView;
import client.utils.DataUtils;


/**
 * Discard controller implementation
 */
public class DiscardController extends Controller implements IDiscardController, Observer {

	private IWaitView waitView;
	private GameState currState;
	private int neededToDiscard;
	private ResourceList currentDiscarding;
	private ResourceList myResources;
	private IRollResultView rollResultView;
	

	/**
	 * DiscardController constructor
	 * 
	 * @param view View displayed to let the user select cards to discard
	 * @param waitView View displayed to notify the user that they are waiting for other players to discard
	 */
	public DiscardController(IDiscardView view, IWaitView waitView, IRollResultView rollResultView, Facade facade) {

		super(view);

		this.waitView = waitView;
		this.rollResultView = rollResultView;
		this.currState = new IsNotTurnState(facade);
		currState.addObserver(this);
	}

	public IDiscardView getDiscardView() {
		return (IDiscardView)super.getView();
	}

	public IWaitView getWaitView() {
		return waitView;
	}

	

	@Override
	public void increaseAmount(ResourceType resource) {
		currentDiscarding.addResource(resource, 1);
		getDiscardView().setStateMessage(currentDiscarding.total() + "/" + neededToDiscard);
		toggleEnabling();
		getDiscardView().setDiscardButtonEnabled(currentDiscarding.total() == neededToDiscard);
		getDiscardView().setResourceDiscardAmount(resource, currentDiscarding.getResource(resource));
	}

	@Override
	public void decreaseAmount(ResourceType resource) {
		currentDiscarding.removeResource(resource, 1);
		getDiscardView().setStateMessage(currentDiscarding.total() + "/" + neededToDiscard);
		toggleEnabling();
		getDiscardView().setDiscardButtonEnabled(currentDiscarding.total() == neededToDiscard);
		getDiscardView().setResourceDiscardAmount(resource, currentDiscarding.getResource(resource));
	}
	
	private void toggleEnabling() {
		boolean belowTotal = currentDiscarding.total() < neededToDiscard;
		getDiscardView().setResourceAmountChangeEnabled(
				ResourceType.BRICK, 
				belowTotal && myResources.getBrick() > currentDiscarding.getBrick(), 
				currentDiscarding.getBrick() > 0);
		getDiscardView().setResourceAmountChangeEnabled(
				ResourceType.SHEEP, 
				belowTotal && myResources.getSheep() > currentDiscarding.getSheep(), 
				currentDiscarding.getSheep() > 0);
		getDiscardView().setResourceAmountChangeEnabled(
				ResourceType.WHEAT, 
				belowTotal && myResources.getWheat() > currentDiscarding.getWheat(), 
				currentDiscarding.getWheat() > 0);
		getDiscardView().setResourceAmountChangeEnabled(
				ResourceType.ORE, 
				belowTotal && myResources.getOre() > currentDiscarding.getOre(), 
				currentDiscarding.getOre() > 0);
		getDiscardView().setResourceAmountChangeEnabled(
				ResourceType.WOOD, 
				belowTotal && myResources.getWood() > currentDiscarding.getWood(), 
				currentDiscarding.getWood() > 0);
		
	}
	

	@Override
	public void discard() {

		synchronized (DataUtils.modelLock) {
			boolean success = currState.discardCards(currentDiscarding);
			if (success)
			{
				
				getDiscardView().closeModal();
				
			} else {
			}
		
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		GameModel model = (GameModel)arg;
		currState = currState.identifyState(model.getTurnTracker());
		IDiscardView view = getDiscardView();
		if (currState instanceof DiscardingState && !view.isModalShowing() && !rollResultView.isModalShowing() && currState.getPlayerResources().total() > 7) {
			
		    myResources = currState.getPlayerResources();
			currentDiscarding = new ResourceList(0,0,0,0,0);
			neededToDiscard = myResources.total() / 2;
			
			getDiscardView().setStateMessage(currentDiscarding.total() + "/" + neededToDiscard);
			
			view.setResourceMaxAmount(ResourceType.WOOD, myResources.getWood());
			view.setResourceMaxAmount(ResourceType.BRICK, myResources.getBrick());
			view.setResourceMaxAmount(ResourceType.ORE, myResources.getOre());
			view.setResourceMaxAmount(ResourceType.SHEEP, myResources.getSheep());
			view.setResourceMaxAmount(ResourceType.WHEAT, myResources.getWheat());
			
			view.setResourceAmountChangeEnabled(ResourceType.WOOD, myResources.getWood() > 0, false);
			view.setResourceAmountChangeEnabled(ResourceType.BRICK, myResources.getBrick() > 0, false);
			view.setResourceAmountChangeEnabled(ResourceType.ORE, myResources.getOre() > 0, false);
			view.setResourceAmountChangeEnabled(ResourceType.SHEEP, myResources.getSheep() > 0, false);
			view.setResourceAmountChangeEnabled(ResourceType.WHEAT, myResources.getWheat() > 0, false);
			
			getDiscardView().setDiscardButtonEnabled(neededToDiscard == 0);
			getDiscardView().setResourceDiscardAmount(ResourceType.WOOD, 0);
			getDiscardView().setResourceDiscardAmount(ResourceType.BRICK, 0);
			getDiscardView().setResourceDiscardAmount(ResourceType.ORE, 0);
			getDiscardView().setResourceDiscardAmount(ResourceType.SHEEP, 0);
			getDiscardView().setResourceDiscardAmount(ResourceType.WHEAT, 0);
			view.showModal();
		}
	}

}

