package torrent.ipc;

import java.io.IOException;
import java.lang.reflect.Method;

public interface ServerConnectionService {

	Object call(Method method, Object[] args) throws Exception;

}
