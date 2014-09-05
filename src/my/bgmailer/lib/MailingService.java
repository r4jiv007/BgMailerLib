package my.bgmailer.lib;

import android.app.IntentService;
import android.content.Intent;
import android.widget.Toast;

public class MailingService extends IntentService {

	private String mUserName, mPasswd, mSubject, mBody;
	private String[] mRecepients;
	private String mFilePath, mFileName;
	private BgMailer mBgMailer;

	public MailingService() {
		super("BgMailer");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		mUserName = intent.getStringExtra(BgMailer.USERKEY);
		mPasswd = intent.getStringExtra(BgMailer.PASSKEY);
		mRecepients = intent.getStringArrayExtra(BgMailer.RECIEVEKEY);
		mSubject = intent.getStringExtra(BgMailer.SUBJECTKEY);
		mBody = intent.getStringExtra(BgMailer.BODYKEY);
		mFilePath = intent.getStringExtra(BgMailer.FILEPATHKEY);
		mFileName = intent.getStringExtra(BgMailer.FILENAMEKEY);
		SetandSend();
	}

	private void SetandSend() {
		mBgMailer = new BgMailerImpl(this);
		mBgMailer.setUserName(mUserName);
		mBgMailer.setPassword(mPasswd);
		mBgMailer.addRecepients(mRecepients);
		mBgMailer.setSubject(mSubject);
		mBgMailer.setBody(mBody);
		mBgMailer.addAttachment(mFilePath, mFileName);

		if (mBgMailer.send()) {
			Toast.makeText(this, "Successfully Mailed", 2000).show();
		} else {
			Toast.makeText(this, "Failed", 2000).show();

		}

	}

}
