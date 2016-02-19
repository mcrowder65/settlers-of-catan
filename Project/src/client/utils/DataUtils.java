package client.utils;

import java.util.List;

import shared.definitions.Player;

public class DataUtils {

	public static Object modelLock = new Object();
	public static int countNumPlayers(Player[] players) {
		for (int n = 0; n < players.length; n++) {
			if (players[n] == null) {
				return n;
			}
		}
		return players.length;
	}
}
