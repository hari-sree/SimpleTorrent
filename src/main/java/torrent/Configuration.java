package torrent;

import java.util.HashMap;

public class Configuration {

	public static final String TRACKER_HOSTNAME = "tracker.hostname";

	public static final String SERVER_HOSTNAME = "server.hostname";
	public static final String SERVER_PORT = "server.port";

	public static final String LOCALHOST = "127.0.0.1";

	HashMap<String, String> map = new HashMap<String, String>();

	public void set(String key, String value) {
		map.put(key, value);
	}

	public String get(String key) {
		return map.get(key);
	}

	public int getInt(String key) {
		return Integer.parseInt(map.get(key));
	}
}
