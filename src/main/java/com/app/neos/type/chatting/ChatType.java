package com.app.neos.type.chatting;

public enum ChatType {
    ENTER("입장"), CHAT("대화"), LEAVE("퇴장");

    private String value;

    private ChatType(String value) {
        this.value = value;
    }

    public String toString() {
        return this.value;
    }

    public static ChatType change(String value) {
        ChatType result = null;
        for (ChatType type : ChatType.values()) {
            if (type.toString().equals(value)) {
                result = type;
                break;
            }
        }
        return result;
    }
}