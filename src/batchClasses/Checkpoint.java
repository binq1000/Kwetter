package batchClasses;

import java.io.Serializable;

/**
 * Created by Nekkyou on 22-3-2017.
 */
public final class Checkpoint implements Serializable {

	private long eventCount;

	public Checkpoint() {
		eventCount = 0;
	}

	public final void eventHappened() {
		++eventCount;
	}

	public final long getCount() {
		return eventCount;
	}
}
