package com.kd.tv.service;

import org.springframework.stereotype.Service;

import com.kd.tv.exception.GenericServiceException;
import com.kd.tv.model.TrustRequestModel;

@Service
public interface TrustRequestService {
	void addTrustRequest(TrustRequestModel trm) throws GenericServiceException; 
	int updateStatus(boolean status);
	boolean findStatus(TrustRequestModel trm);
	String findStatusCode(TrustRequestModel trm);
}
