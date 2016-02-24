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
	
	
	@Override
	public void startBuyCard() {
		
		getBuyCardView().showModal();
		
	}

	@Override
	public void cancelBuyCard() {
		
		getBuyCardView().closeModal();
	}

	@Override
	public void buyCard() {
		boolean success = currState.buyDevCard();
		if(success == true){
			getBuyCardView().closeModal();
		}
	}

	@Override
	public void startPlayCard() {
		int numCards;
		
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
		
		
		getPlayCardView().setCardEnabled(DevCardType.MONUMENT,currState.canUseMonument());
		getPlayCardView().setCardEnabled(DevCardType.ROAD_BUILD,currState.canUseRoadBuilder());
		getPlayCardView().setCardEnabled(DevCardType.MONOPOLY,currState.canUseMonopoly());
		getPlayCardView().setCardEnabled(DevCardType.YEAR_OF_PLENTY,currState.canUseYearOfPlenty());
		getPlayCardView().setCardEnabled(DevCardType.SOLDIER,currState.canUseSoldier());
	}

	@Override
	public void cancelPlayCard() {

		getPlayCardView().closeModal();
	}

	@Override
	public void playMonopolyCard(ResourceType resource) {
		boolean success = currState.useMonopoly(resource);
		if(success == true){
			getPlayCardView().closeModal();
		}
		
	}

	@Override
	public void playMonumentCard() {
		boolean success = currState.playMonument();
		if(success == true){
			getPlayCardView().closeModal();
		}
	}

	@Override
	public void playRoadBuildCard() {
		roadAction.execute();
	}

	@Override
	public void playSoldierCard() {
		
		soldierAction.execute();
	}

	@Override
	public void playYearOfPlentyCard(ResourceType resource1, ResourceType resource2) {
		boolean success = currState.playYearOfPlenty(resource1, resource2);
		if(success == true){
			getPlayCardView().closeModal();
		}
	}

	@Override
	public void update(Observable o, Object arg) {
		GameModel model = (GameModel)arg;
		currState = currState.identifyState(model.getTurnTracker());
		oldDevCards = model.getLocalPlayer(currState.getPlayerId()).getOldDevCards();
		newDevCards = model.getLocalPlayer(currState.getPlayerId()).getNewDevCards();
		
	}

}

