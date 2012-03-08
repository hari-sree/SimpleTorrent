package torrent.server;

import static org.junit.Assert.*;

import java.io.Serializable;

import org.junit.Test;

import torrent.Configuration;
import torrent.ipc.IPCServer;

public class TrackerTest  {

	@Test
	public void canProvideTrackerStatus() {
		TrackerService tracker = new Tracker();
		assertNotNull(tracker.trackerStatus());
	}

	@Test
	public void trackerProxyCanCommunicateWithServer() throws Exception {

		Configuration conf = new Configuration();
		conf.set(Configuration.SERVER_HOSTNAME, Configuration.LOCALHOST);
		conf.set(Configuration.SERVER_PORT, "5005");

		IPCServer server = new IPCServer(conf, new Tracker());
		server.start();

		TrackerService tracker = new TrackerFactory().trackerInstance(conf);
		assertNotNull(tracker);

		assertEquals("finetracker", tracker.trackerStatus().getAddress());

		server.stop();

	}
}
