package torrent.server;

import java.lang.reflect.Proxy;

import torrent.Configuration;

public class TrackerFactory {
	public TrackerService trackerInstance(Configuration conf) throws Exception {

		TrackerService tracker = (TrackerService) Proxy.newProxyInstance(this
				.getClass().getClassLoader(),
				new Class[] { TrackerService.class },
				new ServerInvocationHandler(conf));

		return tracker;

	}
}
