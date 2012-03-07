package torrent.client;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Test;

import torrent.TestHelper;
import torrent.Torrent;
import torrent.server.TrackerService;
import torrent.server.TrackerStatus;

public class ClientTest {

	@Test
	public void callsTrackerForClientRequests() throws Exception {
		TrackerService tracker = mock(TrackerService.class);
		Client client = new Client(tracker);
		client.registerTorrent((new TestHelper()).torrentInstance(null, 70));
		verify(tracker).registerTorrent(any(Torrent.class));
	}

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
