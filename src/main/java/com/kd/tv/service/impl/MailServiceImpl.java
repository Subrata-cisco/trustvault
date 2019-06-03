package com.kd.tv.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kd.tv.dao.SESHelper;
import com.kd.tv.service.MailService;

@Component
public class MailServiceImpl implements MailService {
	
	@Autowired
	private SESHelper sesMailSender;

	@Override
	public String sendMail(String email) {
		return sesMailSender.sendMail(email);
	}

}
