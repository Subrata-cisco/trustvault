package com.kd.tv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kd.tv.dao.TrustVaultRepo;
import com.kd.tv.entity.TrustRequestEntity;
import com.kd.tv.exception.GenericServiceException;
import com.kd.tv.model.TrustRequestModel;
import com.kd.tv.service.TrustRequestService;

@Component
public class TrustRequestServiceImpl implements TrustRequestService {
	
	@Autowired
    private TrustVaultRepo trustVaultRepo;

	@Override
	public void addTrustRequest(TrustRequestModel trm) throws GenericServiceException {
		TrustRequestEntity entity = new TrustRequestEntity();
		
		entity.setTPid(Integer.valueOf(trm.getTPid()));
		entity.setParticipant_uid(trm.getParticipant_uid());
		entity.setTransfer_uid(trm.getTransfer_uid());
		entity.setStatus_code(trm.getStatus_code());
		
		trustVaultRepo.save(entity);
		
	}

	@Override
	public int updateStatus(boolean status) {
		int updated = trustVaultRepo.updateTrustStatus(status);
		return updated;
	}

	@Override
	public boolean findStatus(TrustRequestModel trm) {
		boolean statusCode = false;
		TrustRequestEntity entity = trustVaultRepo.findByParticipantAndTranferId(trm.getParticipant_uid(),trm.getTransfer_uid());
		if(entity != null) {
			statusCode = entity.isStatus();
		}
		return statusCode;
	}

	@Override
	public String findStatusCode(TrustRequestModel trm) {
		String statusCode = null;
		TrustRequestEntity entity = trustVaultRepo.findByParticipantAndTranferId(trm.getParticipant_uid(),trm.getTransfer_uid());
		if(entity != null) {
			statusCode = entity.getStatus_code();
		}
		return statusCode;
	}

}
