package com.example.MokshaMarg.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;



@Service
public class MailService {

@Autowired JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String sender;

	
	public String sendSimpleMail(EmailDetails details) {
		
		try {
			SimpleMailMessage mailMessage=new SimpleMailMessage();
			mailMessage.setFrom(sender);
			mailMessage.setTo(details.getRecipient());
			mailMessage.setText(details.getMsgBody());
			mailMessage.setSubject(details.getSubject());
			
			javaMailSender.send(mailMessage);
			return "email sended";
		}catch (Exception e) {
			 return "Error while Sending Mail";
		}
	}

	
	public String sendMailWithAttachment(EmailDetails details) {
		// TODO Auto-generated method stub
		return null;
	}

}
