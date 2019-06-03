package com.kd.tv.service;

import org.springframework.stereotype.Service;

@Service
public interface SensitiveInfoService {
	boolean saveSensitiveInfo(String tId,String key,String value);
	void getAllSensitiveInfo(String tId); // TBD will implement later , return type should be List of Complex object.
	String getSensitiveInfo(String tId,String key);
}
