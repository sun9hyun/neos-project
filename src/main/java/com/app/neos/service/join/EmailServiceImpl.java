package com.app.neos.service.join;

import java.util.Random;

import javax.mail.Message.RecipientType;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    JavaMailSender emailSender;

    public static final String ePw = createKey();

    private MimeMessage createMessage(String to, String token)throws Exception{
        System.out.println("보내는 대상 : "+ to);
        MimeMessage  message = emailSender.createMimeMessage();
        String url = "http://localhost:10718/send/ok";

        message.addRecipients(RecipientType.TO, to);//보내는 대상
        message.setSubject("NEOS 이메일 인증");//제목

        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 안녕하세요 네오스입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 버튼을 클릭해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<form action="+url+">";
        msgg+= "<input type=\"hidden\" name=\"token\" value="+token+">";
        msgg+= "<button style=\"border: 1px solid #1a7cff; background-color:#1a7cff;width: 350px;height: 50px; color: white; font-size: 1.2rem; font-weight: bold;\">인증 완료</button>";
        msgg+= "</form>";
        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("neos0427@gmail.com","Neos"));//보내는 사람

        return message;
    }

    public static String createKey() {
        StringBuffer key = new StringBuffer();
        Random rnd = new Random();

        for (int i = 0; i < 8; i++) { // 인증코드 8자리
            int index = rnd.nextInt(3); // 0~2 까지 랜덤

            switch (index) {
                case 0:
                    key.append((char) ((int) (rnd.nextInt(26)) + 97));
                    //  a~z  (ex. 1+97=98 => (char)98 = 'b')
                    break;
                case 1:
                    key.append((char) ((int) (rnd.nextInt(26)) + 65));
                    //  A~Z
                    break;
                case 2:
                    key.append((rnd.nextInt(10)));
                    // 0~9
                    break;
            }
        }
        return key.toString();
    }
    @Override
    public String sendSimpleMessage(String to, String token)throws Exception {
        // TODO Auto-generated method stub
        MimeMessage message = createMessage(to,token);
        try{//예외처리
            emailSender.send(message);
        }catch(MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return ePw;
    }
}