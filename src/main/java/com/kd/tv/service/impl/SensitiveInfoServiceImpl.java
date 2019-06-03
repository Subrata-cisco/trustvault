package com.kd.tv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kd.tv.dao.SensitiveInfoRepo;
import com.kd.tv.entity.SensitiveInfoEntity;
import com.kd.tv.service.SensitiveInfoService;

@Component
public class SensitiveInfoServiceImpl implements SensitiveInfoService {
	
	@Autowired
	private SensitiveInfoRepo siRepo;

	@Override
	public boolean saveSensitiveInfo(String tId, String key, String value) {
		boolean status = false;
		
		SensitiveInfoEntity entity = new SensitiveInfoEntity();
		entity.setTransfer_uid(tId);
		entity.setInfo_key(key);
		entity.setInfo_value(value);
		
		SensitiveInfoEntity savedEntity =  siRepo.save(entity);
		System.out.println("****** savedEntity :"+savedEntity.getSid()); 
		
		if(savedEntity != null && savedEntity.getSid() != null){
			status = true;
		}
		
		return status;
	}

	@Override
	public void getAllSensitiveInfo(String tId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getSensitiveInfo(String tId, String key) {
		String value = null;
		SensitiveInfoEntity savedEntity =  siRepo.findByTranferIdAndKey(tId, key);
		if(savedEntity != null) {
			value = savedEntity.getInfo_value();
		}
		return value;
	}

	

}
