// package com.chatapp.queue;

package queue;

import model.Message;

import java.util.concurrent.*;

public class MessageQueueManager {
    // Map userId to a BlockingQueue of messages waiting for delivery
    private final ConcurrentHashMap<String, BlockingQueue<Message>> userQueues = new ConcurrentHashMap<>();

    private static final MessageQueueManager INSTANCE = new MessageQueueManager();

    private MessageQueueManager() {}

    public static MessageQueueManager getInstance() {
        return INSTANCE;
    }

    public void registerUser(String userId) {
        userQueues.putIfAbsent(userId, new LinkedBlockingQueue<>());
    }

    public void unregisterUser(String userId) {
        userQueues.remove(userId);
    }

    public void enqueueMessage(String userId, Message message) {
        BlockingQueue<Message> queue = userQueues.get(userId);
        if (queue != null) {
            queue.offer(message);
        }
    }

    // Simulate message delivery - poll the next message
    public Message pollNextMessage(String userId) {
        BlockingQueue<Message> queue = userQueues.get(userId);
        if (queue != null) {
            return queue.poll();
        }
        return null;
    }
}
