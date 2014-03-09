package com.beiyuan.appyujing.tools;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

public class Tools {

	public static void mToast(Context context,String msg){
		Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
	}
	
	
	/**
	 * 进度框提示
	 * 
	 * @param con
	 * @param msg
	 *            提示的消息内容
	 * @return
	 */
	public static ProgressDialog pd(Context con) {
		return ProgressDialog.show(con, "数据加载中...", "请稍后....", true,
				true);
	}

	public static ProgressDialog pd(Context con, String msg) {
		return ProgressDialog.show(con, "请稍后", msg, true, true);
	}
  //
}
