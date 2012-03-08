package torrent.server;

import java.io.IOException;

import org.junit.Test;

import torrent.Configuration;
import torrent.ipc.IPCServer;

public class ClientHandlerTest {

	@Test
	public void canListenToClientConnections() throws IOException {
		Configuration conf = new Configuration();
		conf.set(Configuration.SERVER_HOSTNAME, Configuration.LOCALHOST);
		conf.set(Configuration.SERVER_PORT, "9002");

		IPCServer server = new IPCServer(conf,new Tracker());
		server.start();

		ServerConnectionFactory.getServerConnection(conf);
		server.stop();
	}

}
