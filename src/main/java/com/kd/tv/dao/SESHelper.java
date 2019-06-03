package com.kd.tv.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

@Component
public class SESHelper {

	final String FROM = "subrata@kickdrumtech.com";
	final String SUBJECT = "[TrustVault] Please verify the code.";
	public static final String MESSAGE_SEND_FAILED_CODE = "MSF";
	
	@Value("${aws.Access.Key.Id}")
	String ACCESS_KEY;
	
	@Value("${aws.Secret.Access.Key}")
	String SECRET_KEY;

	public String sendMail(String to) {
		
		System.out.println("***** ACCESS_KEY :"+ACCESS_KEY);
		String code = getRandomCode(6);
		//String TEXTBODY = " Please use the code ["+ code + "] for verification.";
		String HTMLBODY = "<h1>Trust vault verification code is : "+ code + " </h1>";

		AWSCredentials credentials = new BasicAWSCredentials(ACCESS_KEY, SECRET_KEY);

		try {
			AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard()
					.withCredentials(new AWSStaticCredentialsProvider(credentials)).withRegion(Regions.US_EAST_1)
					.build();

			SendEmailRequest request = new SendEmailRequest().withDestination(new Destination().withToAddresses(to))
					.withMessage(new Message()
							.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(HTMLBODY))
							.withText(new Content().withCharset("UTF-8").withData("")))
							.withSubject(new Content().withCharset("UTF-8").withData(SUBJECT)))
					        .withSource(FROM);

			client.sendEmail(request);
			System.out.println("Email sent!");
		} catch (Exception ex) {
			code = MESSAGE_SEND_FAILED_CODE;
			System.out.println("The email was not sent. Error message: " + ex.getMessage());
		}
		
		return code;
	}

	public static void main(String[] args) {
		//final String to = "manish.kumar@kickdrumtech.com";
		//SESHelper dao = new SESHelper();
		//dao.sendMail(to);
	}

	static String getRandomCode(int n) {
		// choose a Character random from this String
		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(index));
		}
		return sb.toString();
	}

}
