
public class logRecord {

	private String processTime;
	private String user;
	private String state;
	
	public String getProcessTime() {
		return processTime;
	}
	public void setProcessTime(String processTime) {
		this.processTime = processTime;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	public logRecord(String processTime, String user, String state) {
		super();
		this.processTime = processTime;
		this.user = user;
		this.state = state;
	}
	@Override
	public String toString() {
		return "logRecord [processTime=" + processTime + ", user=" + user + ", state=" + state + "]";
	}
}
