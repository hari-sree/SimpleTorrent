package torrent.server;

import static org.junit.Assert.*;

import org.junit.Test;

public class TrackerFactoryTest {

	@Test
	public void canCreateTracker() {
		TrackerService tracker = new TrackerFactory().trackerInstance();
		
	}

}
