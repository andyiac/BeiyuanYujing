package com.beiyuan.appyujing.service;

import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public interface UrlService {

	JSONObject sentParams2Server(JSONObject obj);

	JSONObject sentParams2RegisterServer(JSONObject obj);

	JSONObject sentParams2Complete(JSONObject obj);

	String getJsonContent();

	List<String> getCollegeList(String key, String jsonString);

	JSONArray sentParams2News(JSONObject obj);

}
