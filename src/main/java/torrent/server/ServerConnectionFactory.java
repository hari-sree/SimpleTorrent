package torrent.server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

import torrent.Configuration;
import torrent.ipc.ServerConnection;
import torrent.ipc.ServerConnectionService;

public class ServerConnectionFactory {

	public static ServerConnectionService getServerConnection(Configuration conf)
			throws IOException {
		if (conf == null)
			conf = new Configuration();

		InetAddress serverAddress = InetAddress.getByName(conf
				.get(Configuration.SERVER_HOSTNAME));

		int serverPort = conf.getInt(Configuration.SERVER_PORT);

		Socket sock = new Socket(serverAddress, serverPort);
		return new ServerConnection(sock);
	}
}
