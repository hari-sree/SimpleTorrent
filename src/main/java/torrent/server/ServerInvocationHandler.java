package torrent.server;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import torrent.Configuration;
import torrent.ipc.ServerConnectionService;

public class ServerInvocationHandler implements InvocationHandler {
	ServerConnectionService server;

	public ServerInvocationHandler(Configuration conf) throws IOException {
		server = ServerConnectionFactory.getServerConnection(conf);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		return server.call(method, args);
	}
}