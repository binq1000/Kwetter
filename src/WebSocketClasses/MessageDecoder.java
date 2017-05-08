package WebSocketClasses;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * Created by Nekkyou on 8-5-2017.
 */
public class MessageDecoder implements Decoder.Text<Message> {

	@Override
	public Message decode(String s) throws DecodeException {
		try{
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(s, Message.class);
		} catch(Throwable t){
			throw new DecodeException(s, "json decoding error", t);
		}
	}

	@Override
	public boolean willDecode(String s) {
		return true;
	}

	@Override
	public void init(EndpointConfig endpointConfig) {

	}

	@Override
	public void destroy() {

	}
}
