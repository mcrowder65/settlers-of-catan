package client.utils;

import java.util.List;

public class DataUtils {

	public static int countNumPlayers(List list) {
		for (int n = 0; n < list.size(); n++) {
			if (list.get(n) == null) {
				return n;
			}
		}
		return list.size();
	}
}
