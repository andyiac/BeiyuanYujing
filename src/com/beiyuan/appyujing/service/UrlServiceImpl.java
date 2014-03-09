package com.beiyuan.appyujing.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class UrlServiceImpl implements UrlService {
	private static final String TAG = "UrlServiceImpl";
//	String IP="60.8.194.163:8084";
	String IP="172.18.69.24:8080";
	// -----------------
	/*
	 * 方法的优化参数封装多少参数都能用
	 */
	@Override
	public String sentParams2Server(List<String> paramsKey,
			List<String> paramsValue) {


		String uriAPI = "http://"+IP+"/JWGL_Server_1/LoginServlet";
		
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
			System.out.println("params===="+params.get(i).getValue());
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
				System.out.println("strResult===="+strResult);
				
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
