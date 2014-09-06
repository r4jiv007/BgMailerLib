package my.bgmailer.lib;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

public class MailingService extends IntentService {

	private String mUserName, mPasswd, mSubject, mBody;
	private String[] mRecepients;
	private String mFilePath, mFileName;
	private Mailer mMailer;
	private Handler mHandler;

	public MailingService() {
		super("BgMailer");
		mHandler = new Handler();
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		mUserName = intent.getStringExtra(Mailer.USERKEY);
		mPasswd = intent.getStringExtra(Mailer.PASSKEY);
		mRecepients = intent.getStringArrayExtra(Mailer.RECIEVEKEY);
		mSubject = intent.getStringExtra(Mailer.SUBJECTKEY);
		mBody = intent.getStringExtra(Mailer.BODYKEY);
		mFilePath = intent.getStringExtra(Mailer.FILEPATHKEY);
		mFileName = intent.getStringExtra(Mailer.FILENAMEKEY);
		SetandSend();
	}

	private void SetandSend() {
		mMailer = new MailerImpl(this);
		mMailer.setUserName(mUserName);
		mMailer.setPassword(mPasswd);
		mMailer.addRecepients(mRecepients);
		mMailer.setSubject(mSubject);
		mMailer.setBody(mBody);
		mMailer.addAttachment(mFilePath, mFileName);

		if (mMailer.send()) {
			Log.d("Dmailer", "success");
			mHandler.post(new Toaster(getApplicationContext(), "Success"));
		} else {

			Log.d("Dmailer", "failure");
			mHandler.post(new Toaster(getApplicationContext(), "Failure"));

			// Toast.makeText(this, "Failed", 2000).show();

		}

	}

	private class Toaster implements Runnable {

		private String mMsg;
		private Context mContext;

		public Toaster(Context context, String msg) {
			mMsg = msg;
			mContext = context;
		}

		@Override
		public void run() {
			Toast.makeText(mContext, mMsg, Toast.LENGTH_LONG).show();

		}

	}

}
