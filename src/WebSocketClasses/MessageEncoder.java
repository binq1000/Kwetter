package WebSocketClasses;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created by Nekkyou on 8-5-2017.
 */
public class MessageEncoder implements Encoder.Text<Message> {

	@Override
	public String encode(Message message) throws EncodeException {
		try {
			ObjectMapper mapper = new ObjectMapper();
			return mapper.writeValueAsString(message);
		}
		catch (Throwable t) {
			throw new EncodeException(message, "Json error", t);
		}
	}

	@Override
	public void init(EndpointConfig endpointConfig) {

	}

	@Override
	public void destroy() {

	}
}
