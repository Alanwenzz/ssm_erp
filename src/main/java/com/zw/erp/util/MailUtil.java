package com.zw.erp.util;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
public class MailUtil {
	private JavaMailSender javaMailSender;
	
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	//������
	private String from;

	public void sendMail(String to, String subject, String text) throws MessagingException{
		//1. �����ʼ���Ϣ
		MimeMessage message = javaMailSender.createMimeMessage();
		//2. ʹ��spring�ʼ�������
		MimeMessageHelper helper = new MimeMessageHelper(message);
		//3.�ռ���
		helper.setTo(to);
		//4.������
		helper.setFrom(from);
		//5.�����ʼ��ı���
		helper.setSubject(subject);
		//6.�ʼ�������
		helper.setText(text);
		
		//7.�����ʼ�
		javaMailSender.send(message);
	}
	

	public void setFrom(String from) {
		this.from = from;
	}
}
