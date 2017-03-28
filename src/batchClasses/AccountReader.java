package batchClasses;

import javax.batch.api.chunk.ItemReader;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.stream.JsonParser;
import java.io.InputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Nekkyou on 28-3-2017.
 */
@Dependent
@Named(value = "AccountReader")
public class AccountReader implements ItemReader {

	@Inject
	private JobContext context;

	private String fileName;
	private JsonParser parser;
	private boolean isStarted;

	private Checkpoint checkpoint;


	@Override
	public void open(Serializable serializable) throws Exception {
		if (checkpoint == null) {
			this.checkpoint = new Checkpoint();
		} else {
			this.checkpoint = (Checkpoint) checkpoint;
		}

		fileName = context.getProperties().getProperty("input_file");

		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream iStream = classLoader.getResourceAsStream(fileName);

		parser = Json.createParser(iStream);

		isStarted = false;
		for (long i = 0; i < this.checkpoint.getCount(); ++i) {
			JsonParser.Event event = parser.next();
			if (event == JsonParser.Event.START_ARRAY) {
				isStarted = true;
			}
		}
	}

	@Override
	public void close() throws Exception {
		parser.close();
	}

	@Override
	public Object readItem() throws Exception {
		boolean found = false;
		ImportUser user = new ImportUser();

		System.out.println("Reading item");

		while (!found && parser.hasNext()) {
			JsonParser.Event event = parser.next();
			checkpoint.eventHappened();

			switch (event) {
				case START_ARRAY:
					isStarted = true;
					break;
				case VALUE_STRING:
					if (isStarted) {
						if (user.details == null) {
							user.details = parser.getString();
						}
						else if (user.email == null) {
							user.email = parser.getString();
						}
						else if (user.imagePath == null) {
							user.imagePath = parser.getString();
						}
						else if(user.username == null) {
							user.username = parser.getString();
							found = true;
						}
					}
					break;
				case END_ARRAY:
					user = null;
					break;
			}
		}

		return user;
	}

	@Override
	public Serializable checkpointInfo() throws Exception {
		return null;
	}
}
