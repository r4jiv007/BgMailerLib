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
	private BgMailer mBgMailer;
	private Handler mHandler;

	public MailingService() {
		super("BgMailer");
		mHandler = new Handler();
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
