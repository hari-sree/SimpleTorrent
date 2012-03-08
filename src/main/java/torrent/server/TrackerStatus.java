package torrent.server;

import java.io.Serializable;

public class TrackerStatus implements Serializable {
	String address;

	public String getAddress() {
		return "finetracker";
	}
}
