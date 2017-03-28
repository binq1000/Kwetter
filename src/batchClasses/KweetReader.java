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
 * Created by Nekkyou on 22-3-2017.
 */
@Dependent
@Named("KweetReader")
public class KweetReader implements ItemReader {

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
		ImportKweet kweet = new ImportKweet();
		ImportUser user = new ImportUser();
		String idAsText = null;

		System.out.println("Reading item");

		while (!found && parser.hasNext()) {
			JsonParser.Event event = parser.next();
			checkpoint.eventHappened();

			switch (event) {
				case KEY_NAME:
					break;
				case START_ARRAY:
					isStarted = true;
					break;
				case VALUE_STRING:
					if (isStarted) {
						if(kweet.datePosted == null) {
							String dateAsText = parser.getString();
							SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm a z");
							try {
								kweet.datePosted = sdf.parse(dateAsText);
							}
							catch (Exception ex) {
								ex.printStackTrace();
								kweet.datePosted = new Date();
							}
						}
						else if (kweet.message == null) {
							try {
								kweet.message = parser.getString();
							} catch (Exception e) {
								e.printStackTrace();
							}

						}
						else if (kweet.poster == null) {
							if (user.details == null) {
								user.details = parser.getString();
							}
							else if (user.email == null) {
								user.email = parser.getString();
							}
							else if (user.imagePath == null) {
								user.imagePath = parser.getString();
							}
							else if (user.username == null) {
								user.username = parser.getString();
								kweet.poster = user;
								found = true;
							}
						}
					}
					break;
				case VALUE_NUMBER:
					if (idAsText == null) {
						try {
							kweet.id = parser.getLong();
							idAsText = String.valueOf(kweet.id);
						}
						catch (Exception e) {
							e.printStackTrace();
						}
					}
					break;
				case END_ARRAY:
					kweet = null;
					break;
			}
		}

		return kweet;
	}

	@Override
	public Serializable checkpointInfo() throws Exception {
		return new Checkpoint();
	}
}
