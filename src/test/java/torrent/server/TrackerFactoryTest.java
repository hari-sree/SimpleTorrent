package torrent.server;

import static org.junit.Assert.*;

import org.junit.Test;

import torrent.Configuration;
import torrent.ipc.IPCServer;

public class TrackerFactoryTest {

	@Test
	public void canCreateTracker() throws Exception {
		Configuration conf = new Configuration();
		conf.set(Configuration.SERVER_HOSTNAME, Configuration.LOCALHOST);
		conf.set(Configuration.SERVER_PORT, "9001");

		IPCServer server = new IPCServer(conf, new Tracker());
		server.start();

		TrackerService tracker = new TrackerFactory().trackerInstance(conf);
		assertNotNull(tracker);
		server.stop();
	}

}
