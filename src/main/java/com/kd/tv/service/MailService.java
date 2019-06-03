package com.kd.tv.service;

import org.springframework.stereotype.Service;

@Service
public interface MailService {
	String sendMail(String email); 
}
