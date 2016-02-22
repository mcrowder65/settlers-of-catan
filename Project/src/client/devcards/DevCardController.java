package client.devcards;

import shared.definitions.DevCardList;
import shared.definitions.DevCardType;
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
	private Facade facade;
	
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
		facade.addObserver(this);
		this.facade = facade;
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
		
		getBuyCardView().closeModal();
	}

	@Override
	public void startPlayCard() {
		DevCardList devCards = facade.getDevCards();
		boolean canPlayMonument = currState.canUseMonument();
		if(canPlayMonument == true){
			getPlayCardView().setCardEnabled(DevCardType.MONUMENT,true);
			int numCards = devCards.getMonument();
			getPlayCardView().setCardAmount(DevCardType.MONUMENT, numCards);
			
		}
		else{
			getPlayCardView().setCardEnabled(DevCardType.MONUMENT,false);
		}
		boolean canPlayRoadB = currState.canUseRoadBuilder();
		if(canPlayRoadB == true){
			getPlayCardView().setCardEnabled(DevCardType.ROAD_BUILD,true);
			int numCards = devCards.getRoadBuilding();
			getPlayCardView().setCardAmount(DevCardType.ROAD_BUILD, numCards);
		}
		else{
			getPlayCardView().setCardEnabled(DevCardType.ROAD_BUILD,false);
		}
		boolean canPlayMonopoly = currState.canUseMonopoly();
		if(canPlayMonopoly == true){
			getPlayCardView().setCardEnabled(DevCardType.MONOPOLY,true);
			int numCards = devCards.getMonopoly();
			getPlayCardView().setCardAmount(DevCardType.MONOPOLY, numCards);
		}
		else{
			getPlayCardView().setCardEnabled(DevCardType.MONOPOLY,false);
		}
		boolean canPlayYOP = currState.canUseYearOfPlenty();
		if(canPlayYOP == true){
			getPlayCardView().setCardEnabled(DevCardType.YEAR_OF_PLENTY,true);
			int numCards = devCards.getYearOfPlenty();
			getPlayCardView().setCardAmount(DevCardType.YEAR_OF_PLENTY, numCards);
		}
		else{
			getPlayCardView().setCardEnabled(DevCardType.YEAR_OF_PLENTY,false);
		}
		boolean canPlaySoldierCard = currState.canUseSoldier();
		if(canPlaySoldierCard == true){
			getPlayCardView().setCardEnabled(DevCardType.SOLDIER,true);
			int numCards = devCards.getSoldier();
			getPlayCardView().setCardAmount(DevCardType.SOLDIER, numCards);
		}
		else{
			getPlayCardView().setCardEnabled(DevCardType.SOLDIER,false);
		}
		getPlayCardView().showModal();
	}

	@Override
	public void cancelPlayCard() {

		getPlayCardView().closeModal();
	}

	@Override
	public void playMonopolyCard(ResourceType resource) {
		
	}

	@Override
	public void playMonumentCard() {
		
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
		
	}

	@Override
	public void update(Observable o, Object arg) {
	
	}

}

