package client.main;

import javax.swing.*;

import client.catan.*;
import client.communication.HTTPProxy;
import client.controller.Facade;
import client.data.GameManager;
import client.login.*;
import client.map.IMapController;
import client.join.*;
import client.misc.*;
import client.base.*;

/**
 * Main entry point for the Catan program
 */
@SuppressWarnings("serial")
public class Catan extends JFrame
{
	
	private CatanPanel catanPanel;
	
    
	
	public Catan(Facade facade)
	{
		//TODO general note, meny and i both used purple when playing over a server. - we can't really fix this bc the poller needs to hit it.... let's discuss
		//TODO general note, what happens when you end a game. who takes care of closing the winnerModal
		//TODO do people who roll your number while you have a settlement on that hex do you get the resource too? -> yes, everyone gets resources even if its not your turn
		//TODO when discarding, up arrows stay active even if you've met the quantity to be discarded
		client.base.OverlayView.setWindow(this);
		this.setTitle("Settlers of Catan");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		catanPanel = new CatanPanel(facade);
		this.setContentPane(catanPanel);
		
		display();
	}
	
	private void display()
	{
		pack();
		setVisible(true);
	}
	
	public IMapController getMapController() {
		return catanPanel.getMapController();
	}
	
	//
	// Main
	//
	
	public static void main(final String[] args)
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		SwingUtilities.invokeLater(new Runnable() {
			public void run()
			{
				HTTPProxy proxy = new HTTPProxy(args[0], Integer.parseInt(args[1]));
				GameManager gameManager = new GameManager(proxy,1);
				
				
				
				
				Facade facade = new Facade(proxy, 1, gameManager);
				Catan catan = new Catan(facade);
				
				PlayerWaitingView playerWaitingView = new PlayerWaitingView();
				final PlayerWaitingController playerWaitingController = new PlayerWaitingController(
																									playerWaitingView,
																									facade);
				playerWaitingView.setController(playerWaitingController);
				playerWaitingController.setAllPlayersEnteredAction(new IAction() {
					@Override
					public void execute() 
					{
						catan.catanPanel.enterGame();
					}
					
				});
				
				
				JoinGameView joinView = new JoinGameView();
				NewGameView newGameView = new NewGameView();
				SelectColorView selectColorView = new SelectColorView();
				MessageView joinMessageView = new MessageView();
				final JoinGameController joinController = new JoinGameController(
																				 joinView,
																				 newGameView,
																				 selectColorView,
																				 joinMessageView,
																				 facade);
				joinController.setJoinAction(new IAction() {
					@Override
					public void execute()
					{
						playerWaitingController.start();
					}
				});
			
				joinView.setController(joinController);
				newGameView.setController(joinController);
				selectColorView.setController(joinController);
				joinMessageView.setController(joinController);
				
				LoginView loginView = new LoginView();
				MessageView loginMessageView = new MessageView();
				LoginController loginController = new LoginController(
																	  loginView,
																	  loginMessageView,
																	  facade);
				loginController.setLoginAction(new IAction() {
					@Override
					public void execute()
					{
						joinController.start();
					}
				});
				loginView.setController(loginController);
				loginView.setController(loginController);
				
				loginController.start();
			}
		});
	}
	
}

