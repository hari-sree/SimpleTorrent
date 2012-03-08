package torrent.ipc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import torrent.Configuration;
import torrent.server.ClientHandler;

public class IPCServer implements Runnable {
	ServerSocket socket;
	boolean shouldRun;
	Object serverInstance;

	ExecutorService executor = Executors.newSingleThreadExecutor();
	ExecutorService handlerExecutor = Executors.newCachedThreadPool();

	public IPCServer(Configuration conf, Object serverInstance)
			throws IOException {
		socket = new ServerSocket(conf.getInt(Configuration.SERVER_PORT));
		this.serverInstance = serverInstance;
	}

	public void start() {
		executor.execute(this);
	}

	public boolean stop() {
		try {
			shouldRun = false;
			// TODO interrupt the thread as well
			socket.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

	@Override
	public void run() {
		shouldRun = true;
		while (shouldRun) {
			try {
				Socket clientSock = socket.accept();
				handlerExecutor.execute(new ClientHandler(clientSock,
						serverInstance));
			} catch (Exception e) {
				System.out.println("Exception during Server run " + e);
				stop();
			}
		}
	}
}