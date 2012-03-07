package torrent.client;

import torrent.Torrent;
import torrent.server.TrackerFactory;
import torrent.server.TrackerService;

public class Client {

	TrackerService tracker;

	public Client(TrackerService tracker) {
		this.tracker = tracker;
	}

	public void registerTorrent(Torrent torrent) {
		tracker.registerTorrent(torrent);
	}
}
