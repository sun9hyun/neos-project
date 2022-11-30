package com.app.neos.service.join;

public interface EmailService {
    String sendSimpleMessage(String to, String id)throws Exception;
}
