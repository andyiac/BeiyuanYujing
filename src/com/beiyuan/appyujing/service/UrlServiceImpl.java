package com.beiyuan.appyujing.service;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

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
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class UrlServiceImpl implements UrlService {
	private static final String TAG = "UrlServiceImpl";

	String IP="172.18.69.24:8080";
	// -----------------
	/*
	 * 方法的优化参数封装多少参数都能用
	 */
	@Override
	public String sentParams2Server(List<String> paramsKey,
			List<String> paramsValue) {


		String uriAPI = "http://"+IP+"/JWGL_Server_1/LoginServlet";
//		

		List<NameValuePair> params = new ArrayList<NameValuePair>(2);

		for (int i = 0; i < paramsKey.size(); i++) {

			params.add(new BasicNameValuePair(paramsKey.get(i), paramsValue
					.get(i)));// 返回JSon数据
			System.out.println("params===="+params.get(i).getValue());
		}
		String strResult = null;

		JSONObject obj = new JSONObject();
		
		
		try {
			obj.put("role", "老师");
			obj.put("password", params.get(1).getValue());
			obj.put("username", params.get(0).getValue());
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return strResult;
		}
		
//		HttpEntity entity = HttpUtils.getHttpEntity(
//				"http://172.18.69.34:8080/school5/loginCheck.html", 2, obj);
		Log.i("JSON", obj.toString());
		HttpEntity entity = HttpUtils.getHttpEntity(
				uriAPI, 2, obj);
		 
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
				JSONObject jo2 = new JSONObject(sb.toString());
				Log.i("JSON", jo2.toString());
				return jo2.toString();
				// Message msg = Message.obtain(handler, 1,
				// jo2.getBoolean("isExit"));
				// msg.sendToTarget();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return strResult;

	}
	// -----------------
	/*
	 * 方法的优化参数封装多少参数都能用
	 */
	@Override
	public String sentParams2RegisterServer(List<String> paramsKey,
			List<String> paramsValue) {
		System.out.println("用于注册");
		//String uriAPI = "http://"+IP+"/LoginDemo/login.action";
		String uriAPI = "http://"+IP+"/LoginDemo/register.action";
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
	public String sentParams2Complete(List<String> paramsKey,
			List<String> paramsValue) {
		System.out.println("用于完善信息");
		String uriAPI = "http://"+IP+"/LoginDemo/complete.action";
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
			System.out.println(httpResponse.getStatusLine().getStatusCode());
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
	
	
//	//发布信息
//	@Override
//	public String sentParams2Release_sell(List<String> paramsKey,
//			List<String> paramsValue) {
//		System.out.println("用于完善信息");
//		String uriAPI = "http://"+IP+"/LoginDemo/sellRelease.action";
//		// String uriAPI =
//		
//		/* 建立HTTPost对象 */
//		HttpPost httpRequest = new HttpPost(uriAPI);
//		HttpClient client = new DefaultHttpClient();
//		/*
//		 * NameValuePair实现请求参数的封装
//		 */
//		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
//		for (int i = 0; i < paramsKey.size(); i++) {
//
//			params.add(new BasicNameValuePair(paramsKey.get(i), paramsValue
//					.get(i)));// 返回JSon数据
//
//		}
//
//		String strResult = null;
//		try {
//
//			/* 添加请求参数到请求对象 */
//			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//			/* 处理超时 */
//			client.getParams().setParameter(
//					CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
//			/* 发送请求并等待响应 */
//			HttpResponse httpResponse = client.execute(httpRequest);
//			/* 若状态码为200 ok */
//			System.out.println(httpResponse.getStatusLine().getStatusCode());
//			if (httpResponse.getStatusLine().getStatusCode() == 200) {
//				/* 读返回数据 去掉两头不要的参数 */
//				strResult = EntityUtils.toString(httpResponse.getEntity());
//			} else {
//				System.err.println("Error Response: "
//						+ httpResponse.getStatusLine().toString());
//				strResult = null;
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		return strResult;
//	}
//	
//	
//	
//	@Override
//	public String sentParams2Release_buy(List<String> paramsKey,
//			List<String> paramsValue) {
//		System.out.println("用于完善信息");
//		String uriAPI = "http://"+IP+"/LoginDemo/buyRelease.action";
//		// String uriAPI =
//		
//		/* 建立HTTPost对象 */
//		HttpPost httpRequest = new HttpPost(uriAPI);
//		HttpClient client = new DefaultHttpClient();
//		/*
//		 * NameValuePair实现请求参数的封装
//		 */
//		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
//		for (int i = 0; i < paramsKey.size(); i++) {
//
//			params.add(new BasicNameValuePair(paramsKey.get(i), paramsValue
//					.get(i)));// 返回JSon数据
//
//		}
//
//		String strResult = null;
//		try {
//
//			/* 添加请求参数到请求对象 */
//			httpRequest.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
//			/* 处理超时 */
//			client.getParams().setParameter(
//					CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
//			/* 发送请求并等待响应 */
//			HttpResponse httpResponse = client.execute(httpRequest);
//			/* 若状态码为200 ok */
//			System.out.println(httpResponse.getStatusLine().getStatusCode());
//			if (httpResponse.getStatusLine().getStatusCode() == 200) {
//				/* 读返回数据 去掉两头不要的参数 */
//				strResult = EntityUtils.toString(httpResponse.getEntity());
//			} else {
//				System.err.println("Error Response: "
//						+ httpResponse.getStatusLine().toString());
//				strResult = null;
//			}
//		} catch (ClientProtocolException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		return strResult;
//	}
}