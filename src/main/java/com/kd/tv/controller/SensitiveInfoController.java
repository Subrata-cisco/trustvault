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

import com.kd.tv.model.Status;
import com.kd.tv.service.SensitiveInfoService;

/**
 * Rest controller for passing sensitive info.
 * HTTPS is must . TBD.
 * Data should be encrypted from server and ideally should be saved in HSM kind of store. TBD.
 * 
 * @author subratasaha
 *
 */

@RestController
@RequestMapping("/sensitiveinfo")
@CrossOrigin("*")
public class SensitiveInfoController {
	
	@Autowired
	private SensitiveInfoService sService;
	
	
	@PostMapping("/save")
	public ResponseEntity<Status> saveSensitiveInfo(@RequestParam String transferId, @RequestParam String key,
			@RequestParam String value) {
		Status status = new Status();
		
		boolean saved = sService.saveSensitiveInfo(transferId,key,value);
		if(saved) {
			status.setStatus("true");
			status.setMessage("Sensitive info is saved.");
		}else {
			status.setStatus("false");
			status.setMessage("Sensitive info failed to save.");
		}
		
		return new ResponseEntity<Status>(status, HttpStatus.OK); 
	}
	
	@GetMapping("/receive")
	public ResponseEntity<Status> getSensitiveInfo(@RequestParam String transferId, @RequestParam String key){
		Status status = new Status();
		
		String sValue = sService.getSensitiveInfo(transferId,key);
		if(sValue != null) {
			status.setMessage(sValue);
			status.setStatus("Sensive value for "+sValue+" for key "+key);
		}else {
			status.setMessage("");
			status.setStatus("Sensive value for key "+key+" not found.");
		}
		
		return new ResponseEntity<Status>(status, HttpStatus.OK); 
	}
	
	
	@GetMapping("/receiveall")
	public ResponseEntity<Status> sendMailAndInitiateRequest(@RequestParam String transferId, @RequestParam String key,
			@RequestParam String value) {
		Status status = new Status("false","To be implemented...");
		return new ResponseEntity<Status>(status, HttpStatus.OK); 
	}

}
