package torrent.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

import torrent.ipc.ServerConnection;

/* TODO  Handler */
public class ClientHandler implements Runnable {
	Socket socket;
	private Object serverInstance;

	DataInputStream in;
	DataOutputStream out;

	public ClientHandler() throws IOException {
	}

	public ClientHandler(Socket sock, Object serverInstance) throws IOException {
		this.socket = sock;
		this.serverInstance = serverInstance;
		in = new DataInputStream(socket.getInputStream());
		out = new DataOutputStream(socket.getOutputStream());
	}

	@Override
	public void run() {

		try {
			// Request type
			int request = in.readInt();

			switch (request) {
			case ServerConnection.CALL:
				processMethodCall();
			case ServerConnection.STREAM:
			}
		} catch (Exception e) {
			// Exception handling is crude ..
			System.out.println("Exception while reading from client " + e);
		}

	}

	private void processMethodCall() throws Exception, ClassNotFoundException {

		// Write method name
		String methodName = in.readUTF();

		// Read in the number of parameters
		int paramLength = in.readInt();

		// Read in the parameter types
		Class[] parameterTypes = new Class[paramLength];

		for (int i = 0; i < paramLength; i++) {
			parameterTypes[i] = Class.forName(in.readUTF());
		}

		Object[] params = new Object[paramLength];
		ObjectInputStream obIn = new ObjectInputStream(in);

		// Read in the actual parameters
		for (int i = 0; i < paramLength; i++) {
			params[i] = obIn.readObject();
		}
		Object result = invokeMethod(methodName, parameterTypes, params);

		ObjectOutputStream obOut = new ObjectOutputStream(out);
		obOut.writeObject(result);
	}

	public Object invokeMethod(String methodName, Class[] parameterTypes,
			Object[] params) throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException,
			InvocationTargetException {

		Method method = serverInstance.getClass().getMethod(methodName,
				parameterTypes);

		return method.invoke(serverInstance, params);
	}
}
