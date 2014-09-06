package my.bgmailer.lib;


public interface Mailer {

	public static final String USERKEY = "user", PASSKEY = "pass",
			FILENAMEKEY = "filename", FILEPATHKEY = "filepath",
			RECIEVEKEY = "receiver", BODYKEY = "body", SUBJECTKEY = "subject";

	public void setUserName(String username);

	public void setPassword(String password);

	public void addRecepients(String[] string);

	public void setSubject(String subject);

	public void setBody(String body);

	public boolean addAttachment(String uriTostring, String name);

	public boolean send();

}
