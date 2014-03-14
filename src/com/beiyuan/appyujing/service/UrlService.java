package com.beiyuan.appyujing.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public interface UrlService {

	JSONObject sentParams2Server(JSONObject obj);
	String sentParams2RegisterServer(List<String> paramsKey, List<String> paramsValue);
	String sentParams2Complete(List<String> paramsKey, List<String> paramsValue);
	List<Map<String, Object>> getListMaps(String key, String jsonString);
}
