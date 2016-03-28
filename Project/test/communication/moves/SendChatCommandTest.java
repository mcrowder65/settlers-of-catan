package communication.moves;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import server.Game;
import server.util.*;
import shared.communication.request.*;
import shared.communication.response.GetModelResponse;
import shared.definitions.*;
import shared.locations.*;
public class SendChatCommandTest {

	@Before
	public void setUp() throws Exception {
		new Setups().SetupGame("Playing");
	}

	@Test
	public void test() {
		ServerGameModel model = Game.instance().getGameId(0);
		ServerPlayer[] players = model.getServerPlayers();
		SendChatCommand sendChat = new SendChatCommand(0, "hello world!");
		sendChat.setGameCookie(0);
		GetModelResponse response = sendChat.execute();
		
		assertTrue(response.isSuccess());
		String message = Game.instance().getGameId(0).getChat().getLines()[0].toString();
		assertTrue(message.equals("hello world!"));
	}

}
