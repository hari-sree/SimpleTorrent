package torrent.server;

import torrent.Torrent;

public class Tracker implements TrackerService {

	@Override
	public void registerTorrent(Torrent torrent) {

	}

	@Override
	public TrackerStatus trackerStatus() {
		return new TrackerStatus();
	}

}
