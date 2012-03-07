package torrent.server;

import torrent.Torrent;

public interface TrackerService {

	void registerTorrent(Torrent torrent);

	TrackerStatus trackerStatus();

}
