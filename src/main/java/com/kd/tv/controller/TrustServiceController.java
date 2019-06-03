package com.kd.tv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kd.tv.dao.SESHelper;
import com.kd.tv.exception.GenericServiceException;
import com.kd.tv.model.Status;
import com.kd.tv.model.TrustRequestModel;
import com.kd.tv.service.MailService;
import com.kd.tv.service.TrustRequestService;

@RestController
@RequestMapping("/trust")
@CrossOrigin("*")
public class TrustServiceController {
	
	@Autowired
	private MailService mailService;
	
	@Autowired
	private TrustRequestService trService;
	
	
	@PostMapping("/mail/send")
    public ResponseEntity<Status> sendMailAndInitiateRequest(@RequestParam String tenantId,
    		@RequestParam String partId,@RequestParam String transferId,@RequestParam String mail) {
		//verify email TBD
		Status status = new Status();
		String code = mailService.sendMail(mail);
		Boolean anyError = false;
		
		//Send mail
		if(!code.equals(SESHelper.MESSAGE_SEND_FAILED_CODE)) {
			//System.out.println(tenantId+partId+transferId+mail);
			//if Success then add record in db.
			TrustRequestModel trm = new TrustRequestModel();
			trm.setTPid(tenantId.trim());
			trm.setParticipant_uid(partId.trim());
			trm.setTransfer_uid(transferId.trim());
			trm.setStatus(false);
			trm.setStatus_code(code.trim());
			
			try {
				trService.addTrustRequest(trm);
			}catch(GenericServiceException ex) {
				anyError = true;
			}
			
			if(!anyError) {
				status.setStatus("true");
				status.setMessage("Request initiated.. Please check your mail..");
			}
			
		}else {
			status.setStatus("false");
			status.setMessage("Error in sending mail....try again..");
		}
		
		return new ResponseEntity<Status>(status, HttpStatus.OK); 
    }
	
	@PostMapping("/mail/verify")
	public ResponseEntity<Status> verifyMailCodeAndUpdateStatus(@RequestParam String partId,
			@RequestParam String transferId, @RequestParam String code) {
		Status status = new Status();
		
		TrustRequestModel trm = new TrustRequestModel();
		trm.setParticipant_uid(partId.trim());
		trm.setTransfer_uid(transferId.trim());
		
		String sCode = trService.findStatusCode(trm);
		
		System.out.println("from db :"+sCode+" from request :"+code);
		
		if(sCode!= null && sCode.equals(code)){
			//update the status
			int isUpdated = trService.updateStatus(true);
			System.out.println("**** TrustServiceController.verifyMailCodeAndUpdateStatus() isUpdated :"+isUpdated);
			if(isUpdated == 1) {
				status.setMessage("Code verified. You are trusted...");
				status.setStatus("true");
			}else {
				status.setMessage("Code is correct but update status failed. Please try again.");
				status.setStatus("false");
			}
		}else {
			status.setMessage("Incorrect code. Please use the correct code to verify.");
			status.setStatus("false");
		}
		
		return new ResponseEntity<Status>(status, HttpStatus.OK); 
    }
	
	@GetMapping("/status")
    public ResponseEntity<Status> isTrusted(@RequestParam String partId,@RequestParam String transferId){
		Status status = new Status("false","Requested participant id is not trusted yet");
		
		TrustRequestModel trm = new TrustRequestModel();
		trm.setParticipant_uid(partId.trim());
		trm.setTransfer_uid(transferId.trim());
		
		boolean statusFromDB = trService.findStatus(trm);
		
		System.out.println("from statusFromDB :"+statusFromDB);
		
		if(statusFromDB){
			status.setStatus("true");
			status.setMessage("Requested participant id is trusted.");
		}
		
		return new ResponseEntity<Status>(status, HttpStatus.OK); 
	}
    
	
	

}
