package my.bgmailer.lib;

import android.content.Context;

public class MailerImpl implements Mailer {

	private MailConfig mMail;

	private Context mContext;

	
	public MailerImpl(Context context) {
		mContext = context;
		mMail = new MailConfig();
	}

	@Override
	public void setUserName(String username) {
		mMail.set_user(username);
		mMail.set_from(username);

	}

	@Override
	public void setPassword(String password) {

		mMail.set_passwd(password);

	}

	@Override
	public void addRecepients(String[] recepeints) {
		mMail.set_to(recepeints);
	}

	@Override
	public void setSubject(String subject) {

		mMail.set_subject(subject);
	}

	@Override
	public void setBody(String body) {
		mMail.setBody(body);
	}

	@Override
	public boolean addAttachment(String uriTostring, String name) {
		try {
			mMail.addAttachment(uriTostring, name);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean send() {
		try {
			return mMail.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
