package service;

import java.util.concurrent.ConcurrentHashMap;

import model.User;
import queue.MessageQueueManager;

public class UserService {
    private final ConcurrentHashMap<String, User> users = new ConcurrentHashMap<>();
    private final MessageQueueManager messageQueueManager = MessageQueueManager.getInstance();

    public User registerUser(String userId, String name) {
        User user = new User(userId, name);
        users.put(userId, user);
        messageQueueManager.registerUser(userId);
        return user;
    }

    public void userOnline(String userId){
        User user = users.get(userId);
        if (user != null) {
            user.setOnline(true);
        }
    }
    public void userOffline(String userId) {
        User user = users.get(userId);
        if (user != null) {
            user.setOnline(false);
        }
    }
    public User getUser(String userId) {
        return users.get(userId);
    }

        
}
