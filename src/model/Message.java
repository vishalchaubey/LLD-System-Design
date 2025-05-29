package model;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Message {
    private final String messageId;
    private final String senderId;
    private final String chatRoomId;
    private final String content;
    private final long timestamp;

    // For each recipient, keep track of message status: SENT, DELIVERED, READ
    private final Map<String, MessageStatus> statusPerRecipient = new ConcurrentHashMap<>();

    public Message(String messageId, String senderId, String chatRoomId, String content, long timestamp, Set<String> recipients) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.chatRoomId = chatRoomId;
        this.content = content;
        this.timestamp = timestamp;
        for (String recipient : recipients) {
            statusPerRecipient.put(recipient, MessageStatus.SENT);
        }
    }

    public String getMessageId() { return messageId; }
    public String getSenderId() { return senderId; }
    public String getChatRoomId() { return chatRoomId; }
    public String getContent() { return content; }
    public long getTimestamp() { return timestamp; }

    public Map<String, MessageStatus> getStatusPerRecipient() { return statusPerRecipient; }

    public void updateStatus(String userId, MessageStatus status) {
        statusPerRecipient.put(userId, status);
    }
}
