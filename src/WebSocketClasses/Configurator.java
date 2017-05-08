package WebSocketClasses;

import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Nekkyou on 8-5-2017.
 */
public class Configurator extends ServerEndpointConfig.Configurator {

	private static final Logger LOG = Logger.getLogger(Configurator.class.getName());
	static {
		LOG.setLevel(Level.ALL);
	}

	@Override
	public boolean checkOrigin(String originHeaderValue) {
		LOG.log(Level.FINE, "checking origin for {0}", originHeaderValue);
		return true;
	}

	@Override
	public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
		LOG.log(Level.FINE, "modifying handshake for {0}, {1}, {2}", new Object[]{sec, request, response});
	}
}
