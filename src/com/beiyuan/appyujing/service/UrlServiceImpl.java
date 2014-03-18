package com.beiyuan.appyujing.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class UrlServiceImpl implements UrlService {
	private static final String TAG = "UrlServiceImpl";


	String IP = "172.18.69.24:8080";


	/*
	 * 方法的优化参数封装多少参数都能用
	 */
	@Override
	public JSONObject sentParams2Server(JSONObject obj) {

		String uriAPI = "http://" + IP + "/JWGL_Server_1/LoginServlet";

		// String strResult = "FAIL";

		Log.i("JSON", obj.toString());
		HttpEntity entity = HttpUtils.getHttpEntity(uriAPI, 2, obj);
		JSONObject jo2 = new JSONObject();
		Log.i("JSON", jo2.toString());
		InputStream in = HttpUtils.getInputStream(entity);
		if (in != null) {
			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				StringBuilder sb = new StringBuilder();
				String s = null;
				while ((s = br.readLine()) != null) {
					sb.append(s);
				}
				jo2 = new JSONObject(sb.toString());
				Log.i("JSON", jo2.toString());
				return jo2;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Log.i("JSON", jo2.toString());
		return jo2;

	}

	// -----------------
	/*
	 * 方法的优化参数封装多少参数都能用
	 */
	@Override
	public String sentParams2RegisterServer(List<String> paramsKey,
			List<String> paramsValue) {
		System.out.println("用于注册");
		// String uriAPI = "http://"+IP+"/LoginDemo/login.action";
		String uriAPI = "http://" + IP + "/LoginDemo/register.action";
		// String uriAPI =

		/* 建立HTTPost对象 */
		HttpPost httpRequest = new HttpPost(uriAPI);
		HttpClient client = new DefaultHttpClient();
		/*
		 * NameValuePair实现请求参数的封装
		 */
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		for (int i = 0; i < paramsKey.size(); i++) {

			params.add(new BasicNameValuePair(paramsKey.get(i), paramsValue
					.get(i)));// 返回JSon数据

		}

		String strResult = null;
		try {

			/* 添加请求参数到请求对象 */
			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			/* 处理超时 */
			client.getParams().setParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
			/* 发送请求并等待响应 */
			HttpResponse httpResponse = client.execute(httpRequest);
			/* 若状态码为200 ok */
			if (httpResponse.getStatusLine().getStatusCode() == 200) {
				/* 读返回数据 去掉两头不要的参数 */
				strResult = EntityUtils.toString(httpResponse.getEntity());
			} else {
				System.err.println("Error Response: "
						+ httpResponse.getStatusLine().toString());
				strResult = null;
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return strResult;

	}

	@Override
	public JSONObject sentParams2Complete(JSONObject obj) {
		System.out.println("用于完善信息");
		String uriAPI = "http://" + IP + "/JWGL_Server_1/LoginServlet";

		// String strResult = "FAIL";

		Log.i("JSON", obj.toString());
		HttpEntity entity = HttpUtils.getHttpEntity(uriAPI, 2, obj);
		JSONObject jo2 = new JSONObject();
		Log.i("JSON", jo2.toString());
		InputStream in = HttpUtils.getInputStream(entity);
		if (in != null) {
			try {
				BufferedReader br = new BufferedReader(
						new InputStreamReader(in));
				StringBuilder sb = new StringBuilder();
				String s = null;
				while ((s = br.readLine()) != null) {
					sb.append(s);
				}
				jo2 = new JSONObject(sb.toString());
				Log.i("JSON", jo2.toString());
				return jo2;

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Log.i("JSON", jo2.toString());
		return jo2;
	}

	@Override
	public List<Map<String, Object>> getListMaps(String key, String jsonString) {
		// TODO Auto-generated method stub
		List<Map<String, Object>> listMap = new ArrayList<Map<String, Object>>();
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			JSONArray jsonArray = jsonObject.getJSONArray(key);
			for (int i = 0; i < jsonArray.length(); i++) {
				JSONObject jsonObject2 = jsonArray.getJSONObject(i);
				Map<String, Object> map = new HashMap<String, Object>();
				// 通过org.json中的迭代器来取Map中的值。
				Iterator<String> iterator = jsonObject2.keys();
				while (iterator.hasNext()) {
					String jsonKey = iterator.next();
					Object jsonValue = jsonObject2.get(jsonKey);
					// JSON的值是可以为空的，所以我们也需要对JSON的空值可能性进行判断。
					if (jsonValue == null) {
						jsonValue = "";
					}
					map.put(jsonKey, jsonValue);
				}
				listMap.add(map);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return listMap;
	}
}


