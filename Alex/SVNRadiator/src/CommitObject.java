
public class CommitObject {
	
	private String author;
	private String date;
	private String message;

	public CommitObject(String auth, String dat, String msg) {
		
		author = auth;
		date = dat;
		message = msg;
	}

	public String getAuthor() {
	
		return author;
	}
	
	public String getDate() {
		
		return date;
	}
	
	public String getMessage() {
		
		return message;
	}
}
