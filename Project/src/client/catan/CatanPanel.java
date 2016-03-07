package client.catan;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import shared.definitions.ResourceType;
import client.controller.Facade;
import client.data.GameManager;
import client.discard.DiscardController;
import client.discard.DiscardView;
import client.join.IJoinGameController;
import client.map.IMapController;
import client.misc.WaitView;
import client.roll.RollController;
import client.roll.RollResultView;
import client.roll.RollView;

@SuppressWarnings("serial")
public class CatanPanel extends JPanel
{
	private TitlePanel titlePanel;
	private LeftPanel leftPanel;
	private MidPanel midPanel;
	private RightPanel rightPanel;
	
	private DiscardView discardView;
	private WaitView discardWaitView;
	private DiscardController discardController;
	
	private RollView rollView;
	private RollResultView rollResultView;
	private RollController rollController;
	
	
	
	public CatanPanel(IJoinGameController joinController, Facade facade)
	{
		this.setLayout(new BorderLayout());
		
		rollResultView = new RollResultView();
		
		titlePanel = new TitlePanel();
		midPanel = new MidPanel(rollResultView, facade);
		leftPanel = new LeftPanel(titlePanel, midPanel.getGameStatePanel(),facade);
		rightPanel = new RightPanel(midPanel.getMapController(), this, joinController,  facade);
		
		this.add(titlePanel, BorderLayout.NORTH);
		this.add(leftPanel, BorderLayout.WEST);
		this.add(midPanel, BorderLayout.CENTER);
		this.add(rightPanel, BorderLayout.EAST);
		
	
		
		discardView = new DiscardView();
		discardWaitView = new WaitView();
		discardWaitView.setMessage("Waiting for other Players to Discard");
		discardController = new DiscardController(discardView, discardWaitView, rollResultView, facade);
		discardView.setController(discardController);
		discardWaitView.setController(discardController);
		
		rollView = new RollView();
		
		rollController = new RollController(rollView, rollResultView, facade);
		rollView.setController(rollController);
		rollResultView.setController(rollController);
	
	}
	public IMapController getMapController() {
		return midPanel.getMapController();
	}
	public void enterGame() {
		leftPanel.getHistoryController().enterGame();
		leftPanel.getChatController().enterGame();
		leftPanel.getTurnController().enterGame();
		midPanel.getMapController().enterGame();
		rightPanel.getPointsController().enterGame();
		rightPanel.getResourceController().enterGame();
	
		
	}
	public void leaveGame() {
		leftPanel.getHistoryController().leaveGame();
		leftPanel.getChatController().leaveGame();
		leftPanel.getTurnController().leaveGame();
		midPanel.getMapController().leaveGame();
		rightPanel.getPointsController().leaveGame();
		rightPanel.getResourceController().leaveGame();
	}
}

