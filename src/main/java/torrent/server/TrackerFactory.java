package torrent.server;

import torrent.Torrent;

public class TrackerFactory {
	public TrackerService trackerInstance() {
		return new TrackerService() {

			@Override
			public void registerTorrent(Torrent torrent) {

			}

			@Override
			public TrackerStatus trackerStatus() {
				return null;
			}
		};
	}
}
