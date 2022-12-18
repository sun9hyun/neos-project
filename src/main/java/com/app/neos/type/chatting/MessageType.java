package com.app.neos.type.chatting;

import com.app.neos.type.alarm.ReadStatus;

public enum MessageType {
    READ("읽음"), UNREAD("안읽음");

    private String value;

    private MessageType(String value){this.value = value;}

    public String toString(){
        return this.value;
    }

    public static MessageType change(String value){
        MessageType result = null;
        for(MessageType status : MessageType.values()){
            if(status.toString().equals(value)){
                result = status;
                break;
            }
        }
        return result;
    }
}
