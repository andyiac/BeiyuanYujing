package com.beiyuan.appyujing.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;
import com.beiyuan.appyujing.R;
import com.beiyuan.appyujing.data.JsonParser;
import com.beiyuan.appyujing.data.MyClass;
import com.beiyuan.appyujing.data.VirtualData;
import com.beiyuan.appyujing.view.TitleVoiceView;
import com.beiyuan.appyujing.view.TitleVoiceView.OnBackButtonClickListener;
import com.beiyuan.appyujing.view.TitleVoiceView.OnVoicetButtonClickListener;
import com.iflytek.cloud.speech.RecognizerResult;
import com.iflytek.cloud.speech.SpeechConstant;
import com.iflytek.cloud.speech.SpeechError;
import com.iflytek.cloud.speech.SpeechListener;
import com.iflytek.cloud.speech.SpeechUser;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class VoiceMessageActivity extends MyActivity {
	
	private TitleVoiceView mTitleVoiceView;
	private SharedPreferences mSharedPreferences;
	private final String APP_ID = "appid=53230169";
	private RecognizerDialog dialog;
	private EditText textContent;
	private Intent intentMessage;
	private SimpleAdapter adapterMessage;
	private List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
	private ListView messageListview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.voice_call_view);
		mSharedPreferences = getSharedPreferences(getPackageName(),
				Context.MODE_PRIVATE);
		mTitleVoiceView = (TitleVoiceView) findViewById(R.id.title_voice_call);
		textContent = (EditText) findViewById(R.id.voice_text);
		messageListview = (ListView) findViewById(R.id.call_listview);
		mTitleVoiceView.setLeftButton(new OnBackButtonClickListener() {

			@Override
			public void onClick(View button) {
				// TODO Auto-generated method stub
				VoiceMessageActivity.this.finish();
			}
		});
		mTitleVoiceView.setRightButton(new OnVoicetButtonClickListener() {

			@Override
			public void onClick(View button) {
				// TODO Auto-generated method stub
				showContactsNum();
			}
		});

	}

	public void showContactsNum() {
		// 获取引擎参数
		String engine = mSharedPreferences.getString(
				getString(R.string.preference_key_iat_engine),
				getString(R.string.preference_default_iat_engine));
		// 用户登录
		SpeechUser.getUser().login(this, null, null, APP_ID, loginListener);
		// 默认dialog
		dialog = new RecognizerDialog(this);
		dialog.setParameter(SpeechConstant.CLOUD_GRAMMAR, null);
		dialog.setParameter(SpeechConstant.DOMAIN, engine);
		// 去除所有标点符号
		// dialog.setParameter(SpeechConstant.PARAMS, "asr_ptt=0");
		// 设置采样率参数
		dialog.setParameter(SpeechConstant.SAMPLE_RATE, "8000");
		dialog.setListener(recognizerDialogListener);
		dialog.show();

	}

	private SpeechListener loginListener = new SpeechListener() {

		@Override
		public void onCompleted(SpeechError arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onData(byte[] arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onEvent(int arg0, Bundle arg1) {
			// TODO Auto-generated method stub

		}

	};
	RecognizerDialogListener recognizerDialogListener = new RecognizerDialogListener() {

		@Override
		public void onResult(RecognizerResult results, boolean isLast) {
			// TODO Auto-generated method stub

			String text = JsonParser.parseIatResult(results.getResultString());
			// 去标点符号
			textContent.append(text.replaceAll("。", ""));
			Log.e("mytag", "textContent=====" + text);
			// 定位编辑的光标位置
			textContent.setSelection(textContent.length());
			if (isNumeric(textContent.getText().toString())) {
				Uri smsToUri = Uri.parse("smsto:");
				Intent sendIntent = new Intent(Intent.ACTION_VIEW, smsToUri);
				sendIntent
						.putExtra("address", textContent.getText().toString()); // 电话号码，这行去掉的话，默认就没有电话
				sendIntent.setType("vnd.android-dir/mms-sms");
				startActivity(sendIntent);
				textContent.setText("");
			} else {
				searchMessageContact();
			}

		}

		@Override
		public void onError(SpeechError arg0) {
			// TODO Auto-generated method stub

		}
	};

	// 查询并拨号
	@SuppressLint("NewApi")
	public void searchMessageContact() {
		intentMessage = new Intent();

		getFuzzyQueryByName(textContent.getText().toString());
		Log.e("mytag", "data.size====" + data.size());

		adapterMessage = new SimpleAdapter(this, data,
				R.layout.call_listview_item, new String[] { "name", "phone" },
				new int[] { R.id.item, R.id.phone });

		messageListview.setAdapter(adapterMessage);
		adapterMessage.notifyDataSetChanged();
		if (data.size() >= 1) {
			messageListview.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					// TODO Auto-generated method stub
					TextView phone = (TextView) arg1.findViewById(R.id.phone);
					String phonename = phone.getText().toString();
					Log.e("mytag", "phone=====" + phonename);
					Uri smsToUri = Uri.parse("smsto:");
					Intent sendIntent = new Intent(Intent.ACTION_VIEW, smsToUri);
					sendIntent.putExtra("address", phonename); // 电话号码，这行去掉的话，默认就没有电话
					sendIntent.setType("vnd.android-dir/mms-sms");
					startActivity(sendIntent);
					textContent.setText("");
					adapterMessage.notifyDataSetChanged();
				}
			});
		} else {
			Toast.makeText(this, "没有找到此人", Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * 根据名字中的某一个字进行模糊查询
	 * 
	 * @param key
	 */
	@SuppressLint("NewApi")
	private void getFuzzyQueryByName(String key) {

		StringBuilder sb = new StringBuilder();
		ContentResolver cr = getContentResolver();
		String[] projection = { ContactsContract.PhoneLookup.DISPLAY_NAME,
				ContactsContract.CommonDataKinds.Phone.NUMBER };
		Cursor cursor = cr.query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, projection,
				ContactsContract.Contacts.DISPLAY_NAME + " like " + "'%" + key
						+ "%'", null, null);
		while (cursor.moveToNext()) {

			String name = cursor.getString(cursor
					.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
			String number = cursor
					.getString(cursor
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
			sb.append(name + " (").append(number + ")").append("\r\n");
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("name", name);
			map.put("phone", number);
			data.add(map);
		}
	}

	/**
	 * 判断是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		return pattern.matcher(str).matches();
	}


}
