package com.GestionAbsence.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;


@Component
public class SendMailService {
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	void sendEmail(String mail,String subject, String context) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("belgacem.ayoub8590@gmail.com");

        msg.setSubject(subject);
        msg.setText(context);

        javaMailSender.send(msg);

    }
	


}
