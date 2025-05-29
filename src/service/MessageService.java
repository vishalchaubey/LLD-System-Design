// package com.chatapp.service;

package service;

import model.*;
import queue.MessageQueueManager;
import java.util.Map;
import java.util.UUID;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class MessageService {
    private final ConcurrentHashMap<String, Message> messageStore = new ConcurrentHashMap<>();
    private final MessageQueueManager messageQueueManager = MessageQueueManager.getInstance();

    public Message sendMessage(String senderId, String chatRoomId, String content, Set<String> recipients) {
        String messageId = UUID.randomUUID().toString();
        Message message = new Message(messageId, senderId, chatRoomId, content, System.currentTimeMillis(), recipients);
        messageStore.put(messageId, message);

        // Enqueue message to all recipients (thread-safe)
        for (String recipientId : recipients) {
            messageQueueManager.enqueueMessage(recipientId, message);
        }
        return message;
    }

    public void updateMessageStatus(String messageId, String userId, MessageStatus status) {
        Message message = messageStore.get(messageId);
        if (message != null) {
            message.updateStatus(userId, status);
        }
    }

    public Map<String, MessageStatus> getMessageStatus(String messageId) {
        Message message = messageStore.get(messageId);
        if (message != null) {
            return message.getStatusPerRecipient();
        }
        return null;
    }
}
