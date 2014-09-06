package my.bgmailer.lib;

import android.content.Context;
import android.content.Intent;

public class MailerBackgroundImpl implements Mailer {

	private String mUserName, mPasswd, mSubject, mBody;
	private String[] mRecepients;
	private String mFilePath, mFileName;
	private Context mContext;

	public MailerBackgroundImpl(Context context) {
		mContext = context;
	}

	@Override
	public void setUserName(String username) {
		mUserName = username;
	}

	@Override
	public void setPassword(String password) {
		mPasswd = password;
	}

	@Override
	public void addRecepients(String[] receivers) {
		mRecepients = receivers;
	}

	@Override
	public void setSubject(String subject) {
		mSubject = subject;
	}

	@Override
	public void setBody(String body) {
		mBody = body;
	}

	@Override
	public boolean addAttachment(String uriTostring, String name) {
		mFilePath = uriTostring;
		mFileName = name;
		return true;
	}

	@Override
	public boolean send() {
		Intent intent = new Intent(mContext, MailingService.class);
		// in.putExtra(name, value)
		intent.putExtra(Mailer.USERKEY, mUserName);
		intent.putExtra(Mailer.PASSKEY, mPasswd);
		intent.putExtra(Mailer.RECIEVEKEY, mRecepients);
		intent.putExtra(Mailer.SUBJECTKEY, mSubject);
		intent.putExtra(Mailer.BODYKEY, mBody);
		intent.putExtra(Mailer.FILEPATHKEY, mFilePath);
		intent.putExtra(Mailer.FILENAMEKEY, mFileName);
		if (null != mContext.startService(intent))
			return true;
		return false;
	}

}
