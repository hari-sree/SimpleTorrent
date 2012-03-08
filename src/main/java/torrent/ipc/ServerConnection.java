package torrent.ipc;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

/* Manages connection with the server */
public class ServerConnection implements ServerConnectionService {

	public static final int CALL = 0;
	public static final int STREAM = 1;

	Socket sock;
	DataInputStream in;
	DataOutputStream out;

	public ServerConnection(Socket sock) throws IOException {
		this.sock = sock;
		in = new DataInputStream(sock.getInputStream());
		out = new DataOutputStream(sock.getOutputStream());
	}

	@Override
	public Object call(Method method, Object[] params) throws Exception {

		// Request type
		out.writeInt(ServerConnection.CALL);

		// Write method name
		out.writeUTF(method.getName());

		// Write out the number of parameters
		int paramLength = (params == null) ? 0 : params.length;
		out.writeInt(paramLength);

		// Write out the parameter types
		Class[] parameterTypes = method.getParameterTypes();

		for (Class type : parameterTypes) {
			out.writeUTF(type.getName());
		}
		ObjectOutputStream obOut = new ObjectOutputStream(
				sock.getOutputStream());

		// Write out the actual parameters
		for (int i = 0; i < paramLength; i++) {
			obOut.writeObject(params[i]);
		}
		// obOut.close();

		ObjectInputStream obIn = new ObjectInputStream(sock.getInputStream());
		// TODO exception check
		return obIn.readObject();
	}
}
