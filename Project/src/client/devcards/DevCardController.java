package client.devcards;

import shared.definitions.DevCardList;
import shared.definitions.DevCardType;
import shared.definitions.GameModel;
import shared.definitions.ResourceType;

import java.util.Observable;
import java.util.Observer;

import client.base.*;
import client.controller.Facade;
import client.data.GameManager;
import client.gamestate.GameState;
import client.gamestate.IsNotTurnState;
import client.gamestate.PlayingState;


/**
 * "Dev card" controller implementation
 */
public class DevCardController extends Controller implements IDevCardController, Observer {

	private IBuyDevCardView buyCardView;
	private IAction soldierAction;
	private IAction roadAction;
	private GameState currState;
	private DevCardList oldDevCards;
	private DevCardList newDevCards;
	
	/**
	 * DevCardController constructor
	 * 
	 * @param view "Play dev card" view
	 * @param buyCardView "Buy dev card" view
	 * @param soldierAction Action to be executed when the user plays a soldier card.  It calls "mapController.playSoldierCard()".
	 * @param roadAction Action to be executed when the user plays a road building card.  It calls "mapController.playRoadBuildingCard()".
	 */
	public DevCardController(IPlayDevCardView view, IBuyDevCardView buyCardView, 
								IAction soldierAction, IAction roadAction, Facade facade) {

		super(view);
		
		this.buyCardView = buyCardView;
		this.soldierAction = soldierAction;
		this.roadAction = roadAction;
		this.currState = new IsNotTurnState(facade);
		currState.addObserver(this);
	}

	public IPlayDevCardView getPlayCardView() {
		return (IPlayDevCardView)super.getView();
	}

	public IBuyDevCardView getBuyCardView() {
		return buyCardView;
	}
	
	/**
	 * shows the modal for buying a devCard
	 */
	@Override
	public void startBuyCard() {
		
		getBuyCardView().showModal();
		
	}

	/**
	 * closing the modal for buyDevCard
	 */
	@Override
	public void cancelBuyCard() {
		
		getBuyCardView().closeModal();
	}

	/**
	 * closes the buy card modal 
	 */
	@Override
	public void buyCard() {
		boolean success = currState.buyDevCard();
		if(success == true){
			getBuyCardView().closeModal();
		}
	}

	/**
	 * shows and populates the modal for playing a dev card
	 */
	@Override
	public void startPlayCard() {
		int numCards;
		
		//sets the amount of cards they have 
		numCards = oldDevCards.getMonument() + newDevCards.getMonument();
		getPlayCardView().setCardAmount(DevCardType.MONUMENT, numCards);
		numCards = oldDevCards.getRoadBuilding() + newDevCards.getRoadBuilding();
		getPlayCardView().setCardAmount(DevCardType.ROAD_BUILD, numCards);
		numCards = oldDevCards.getMonopoly() + newDevCards.getMonopoly();
		getPlayCardView().setCardAmount(DevCardType.MONOPOLY, numCards);
		numCards = oldDevCards.getYearOfPlenty() + newDevCards.getYearOfPlenty();
		getPlayCardView().setCardAmount(DevCardType.YEAR_OF_PLENTY, numCards);
		numCards = oldDevCards.getSoldier() + newDevCards.getSoldier();
		getPlayCardView().setCardAmount(DevCardType.SOLDIER, numCards);
		
		
		//greys in and out the dev card modals 
		getPlayCardView().setCardEnabled(DevCardType.MONUMENT,currState.canUseMonument());
		getPlayCardView().setCardEnabled(DevCardType.ROAD_BUILD,currState.canUseRoadBuilder());
		getPlayCardView().setCardEnabled(DevCardType.MONOPOLY,currState.canUseMonopoly());
		getPlayCardView().setCardEnabled(DevCardType.YEAR_OF_PLENTY,currState.canUseYearOfPlenty());
		getPlayCardView().setCardEnabled(DevCardType.SOLDIER,currState.canUseSoldier());
		
		getPlayCardView().showModal();
	}

	/**
	 * close model when they hit cancel
	 */
	@Override
	public void cancelPlayCard() {

		getPlayCardView().closeModal();
	}

	/**
	 * plays the monopoly card - sends it to the server
	 */
	@Override
	public void playMonopolyCard(ResourceType resource) {
		boolean success = currState.useMonopoly(resource);
		if(success == true){
			getPlayCardView().closeModal();
		}
		
	}

	/**
	 * plays monument card - sends it to the server
	 */
	@Override
	public void playMonumentCard() {
		boolean success = currState.playMonument();
		if(success == true){
			getPlayCardView().closeModal();
		}
	}

	/**
	 * this will call the mapController to pop up the placeRoad modal
	 */
	@Override
	public void playRoadBuildCard() {
		roadAction.execute();
	}

	/**
	 * calls the mapControler to pop up the modal to move th robber
	 */
	@Override
	public void playSoldierCard() {
		
		soldierAction.execute();
	}

	/**
	 * plays YOP card - sends it to the server
	 */
	@Override
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
		boolean success = currState.playYearOfPlenty(resource1, resource2);
		if(success == true){
			getPlayCardView().closeModal();
		}
	}

	/**
	 * updates the gameModel and updates the currentState
	 */
	@Override
	public void update(Observable o, Object arg) {
		GameModel model = (GameModel)arg;
		currState = currState.identifyState(model.getTurnTracker());
		oldDevCards = model.getLocalPlayer(currState.getPlayerId()).getOldDevCards();
		newDevCards = model.getLocalPlayer(currState.getPlayerId()).getNewDevCards();
		
	}

}

