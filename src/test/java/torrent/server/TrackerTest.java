package torrent.server;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrackerTest {

	@Test
	public void canProvideTrackerStatus() {
		TrackerService tracker = new Tracker();
		assertNotNull(tracker.trackerStatus());
	}

}
