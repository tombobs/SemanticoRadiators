//A class to represent a single commit and store its details 

public class CommitObject {
	
	private String project;
	private String author;
	private String date;
	private String message;

	public CommitObject(String path, String auth, String dat, String msg) {
		
		project = path;
		author = auth;
		date = dat;
		message = msg;
	}

	public String getProject() {
		return project;
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
