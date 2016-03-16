package client.main;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.swing.*;

import client.catan.*;
import client.communication.HTTPProxy;
import client.controller.Facade;
import client.data.GameManager;
import client.login.*;
import client.map.IMapController;
import client.join.*;
import client.misc.*;
import client.utils.JsonTranslator;
import client.base.*;

/**
 * Main entry point for the Catan program
 */
@SuppressWarnings("serial")
public class Catan extends JFrame
{

	private CatanPanel catanPanel;



	public Catan(IJoinGameController joinController, Facade facade)
	{
		client.base.OverlayView.setWindow(this);
		this.setTitle("Settlers of Catan");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		catanPanel = new CatanPanel(joinController, facade);
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
		
		JsonTranslator.translate();
		
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
				//default host & port
				String host = "localhost";
				int port = 8081;
				if (args.length > 0) {
					host = args[0];
					if (args.length > 1) {
						port = Integer.parseInt(args[1]);
					}
				}
				HTTPProxy proxy = new HTTPProxy(host, port);
				GameManager gameManager = new GameManager(proxy,1);




				Facade facade = new Facade(proxy, 1, gameManager);



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


				Catan catan = new Catan(joinController, facade);

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

