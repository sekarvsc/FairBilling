import java.util.List;

public class Result {

	private String user;
	private int session;
	private int time;
	private String lastState;
	private List<logRecord> lsLogRecord;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getSession() {
		return session;
	}
	public void setSession(int session) {
		this.session = session;
	}
	public int getTime() {
		return time;
	}
	public void setTime(int time) {
		this.time = time;
	}
	public String getLastState() {
		return lastState;
	}
	public void setLastState(String lastState) {
		this.lastState = lastState;
	}
	
	public List<logRecord> getLsLogRecord() {
		return lsLogRecord;
	}
	public void setLsLogRecord(List<logRecord> lsLogRecord) {
		this.lsLogRecord = lsLogRecord;
	}
	public Result(String user, int session, int time, String lastState, List<logRecord> lsLogRecord) {
		super();
		this.user = user;
		this.session = session;
		this.time = time;
		this.lastState = lastState;
		this.lsLogRecord = lsLogRecord;
	}
}
