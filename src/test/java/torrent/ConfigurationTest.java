package torrent;

import static org.junit.Assert.*;

import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void canStoreConfiguration() {
		Configuration conf = new Configuration();
		conf.set(Configuration.TRACKER_HOSTNAME, Configuration.LOCALHOST);
		assertEquals(Configuration.LOCALHOST,
				conf.get(Configuration.TRACKER_HOSTNAME));
	}

}
