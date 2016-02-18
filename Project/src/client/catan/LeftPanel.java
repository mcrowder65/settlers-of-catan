package client.catan;

import java.awt.*;
import javax.swing.*;

import client.communication.*;
import client.controller.Facade;
import client.data.GameManager;
import client.turntracker.*;


@SuppressWarnings("serial")
public class LeftPanel extends JPanel {

	private JTabbedPane tabPane;
	private GameHistoryView historyView;
	private GameHistoryController historyController;
	private ChatView chatView;
    private ChatController chatController;
	private TurnTrackerView turnView;
	private TurnTrackerController turnController;
	
	public GameHistoryController getHistoryController() {
		return historyController;
	}

	public ChatController getChatController() {
		return chatController;
	}

	public TurnTrackerController getTurnController() {
		return turnController;
	}

	public LeftPanel(TitlePanel titlePanel, GameStatePanel gameStatePanel, Facade facade) {
		
		this.setLayout(new BorderLayout());
		
		tabPane = new JTabbedPane();
		Font font = tabPane.getFont();
		Font newFont = font.deriveFont(font.getStyle(), 20);
		tabPane.setFont(newFont);
		
		historyView = new GameHistoryView();
		historyController = new GameHistoryController(historyView);
		historyView.setController(historyController);
		
		chatView = new ChatView();
        chatController = new ChatController(chatView, facade);
        chatView.setController(chatController);
		
		turnView = new TurnTrackerView(titlePanel, gameStatePanel);
		turnController = new TurnTrackerController(turnView, facade);
		turnView.setController(turnController);
		
//		gameStatePanel.setController(turnController);
		
		tabPane.add("Game History", historyView);
		tabPane.add("Chat Messages", chatView);
		
		this.add(tabPane, BorderLayout.CENTER);
		this.add(turnView, BorderLayout.SOUTH);

		this.setPreferredSize(new Dimension(350, 700));
	}

	public GameHistoryView getHistoryView() {
		return historyView;
	}

	public ChatView getChatView() {
		return chatView;
	}

	public TurnTrackerView getTurnView() {
		return turnView;
	}

}


