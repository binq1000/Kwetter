package bean;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Nekkyou on 27-3-2017.
 */
@Named(value = "batchBean")
@SessionScoped
public class BatchBean implements Serializable{
	private String NOTSTARTED = "NOT STARTED";

	private long execID;
	private JobOperator jobOperator;
	private static final long serialVersionUID = 3712686178567411830L;

	/* --JSF navigation method--
	 * Starts the batch job and navigates to the next page */
	public String startBatchJob() {
		jobOperator = BatchRuntime.getJobOperator();
		execID = jobOperator.start("kweetimport", null);
		return "batch";
	}

	public String startUserImport() {
		jobOperator = BatchRuntime.getJobOperator();
		execID = jobOperator.start("userimport", null);
		return "batch";
	}

	/* Show the current status of the job */
	public BatchStatus getJobStatus() {
		return jobOperator.getJobExecution(execID).getBatchStatus();
	}

	public String getJobStatusString() {
		if (jobOperator == null) {
			return NOTSTARTED;
		} else {
			return jobOperator.getJobExecution(execID).getBatchStatus().toString();
		}
	}

	public boolean isCompleted() {
		return getJobStatusString().equals(BatchStatus.COMPLETED.toString());
	}

	public String getNOTSTARTED() {
		return NOTSTARTED;
	}

	public void setNOTSTARTED(String NOTSTARTED) {
		this.NOTSTARTED = NOTSTARTED;
	}

}
