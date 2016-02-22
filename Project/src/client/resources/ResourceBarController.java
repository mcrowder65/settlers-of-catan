package client.resources;

import java.util.*;

import client.base.*;
import client.controller.Facade;
import client.data.GameManager;
import client.gamestate.GameState;
import client.gamestate.IsNotTurnState;
import shared.definitions.GameModel;
import shared.definitions.Player;
import shared.definitions.ResourceList;
import shared.definitions.TurnTracker;


/**
 * Implementation for the resource bar controller
 */
public class ResourceBarController extends Controller implements IResourceBarController,Observer {

	private Map<ResourceBarElement, IAction> elementActions;
	private GameState currState;
	private Facade facade;

	public ResourceBarController(IResourceBarView view, Facade facade) {

		super(view);

		elementActions = new HashMap<ResourceBarElement, IAction>();
		this.currState = new IsNotTurnState(facade);
		
		this.facade = facade;


	}

	@Override
	public IResourceBarView getView() {
		return (IResourceBarView)super.getView();
	}

	/**
	 * Sets the action to be executed when the specified resource bar element is clicked by the user
	 * 
	 * @param element The resource bar element with which the action is associated
	 * @param action The action to be executed
	 */
	public void setElementAction(ResourceBarElement element, IAction action) {

		elementActions.put(element, action);
		
	}



	@Override
	public void buildRoad() {
		executeElementAction(ResourceBarElement.ROAD);
	}

	@Override
	public void buildSettlement() {
		executeElementAction(ResourceBarElement.SETTLEMENT);
	}

	@Override
	public void buildCity() {
		executeElementAction(ResourceBarElement.CITY);
	}

	@Override
	public void buyCard() {
		executeElementAction(ResourceBarElement.BUY_CARD);
	}

	@Override
	public void playCard() {
		executeElementAction(ResourceBarElement.PLAY_CARD);
	}

	private void executeElementAction(ResourceBarElement element) {

		if (elementActions.containsKey(element)) {

			IAction action = elementActions.get(element);
			action.execute();
		}
	}
	
	private void populateResources(GameModel model) {
		Player player = model.getLocalPlayer(facade.getPlayerId());
		ResourceList resource = player.getResources();
		
		int wood = resource.getWood();
		int brick = resource.getBrick();
		int sheep = resource.getSheep();
		int wheat = resource.getWheat();
		int ore = resource.getOre();
		
		getView().setElementAmount(ResourceBarElement.WOOD, wood);
		getView().setElementAmount(ResourceBarElement.BRICK, brick);
		getView().setElementAmount(ResourceBarElement.SHEEP, sheep);
		getView().setElementAmount(ResourceBarElement.WHEAT, wheat);
		getView().setElementAmount(ResourceBarElement.ORE, ore);
		
		
		
		int roads = player.getRoads();
		int settlements = player.getSettlements();
		int cities = player.getCities();
		
		getView().setElementAmount(ResourceBarElement.ROAD, roads);
		getView().setElementEnabled(ResourceBarElement.ROAD, player.canBuildRoad());
		getView().setElementAmount(ResourceBarElement.SETTLEMENT, settlements);
		getView().setElementEnabled(ResourceBarElement.SETTLEMENT, player.canBuildSettlement());
		getView().setElementAmount(ResourceBarElement.CITY, cities);
		getView().setElementEnabled(ResourceBarElement.CITY, player.canBuildCity());
		
		boolean clickable = facade.canBuyDevCard();
		getView().setElementEnabled(ResourceBarElement.BUY_CARD, true);
		
		int soldiers = player.getSoldiers();
		getView().setElementAmount(ResourceBarElement.SOLDIERS, soldiers);
		
		
	}

	@Override
	public void update(Observable o, Object arg) {
		GameModel model = (GameModel)arg;
		String state = model.getTurnTracker().getStatus();
		TurnTracker turn = model.getTurnTracker();
		this.currState = currState.identifyState(turn);
		
		
		
		this.populateResources(model);

	}


	public void enterGame() {
		this.facade.addObserver(this);
		
	}
	public void leaveGame() {
		this.facade.deleteObserver(this);
		
	}
}

