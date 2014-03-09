package com.beiyuan.appyujing.service;

import java.util.List;

public interface UrlService {

	String sentParams2Server(List<String> paramsKey, List<String> paramsValue);
	String sentParams2RegisterServer(List<String> paramsKey, List<String> paramsValue);
	String sentParams2Complete(List<String> paramsKey, List<String> paramsValue);
}
