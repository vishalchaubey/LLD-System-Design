package model;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class User {
    private final String userId;
    private final String name;
    private volatile boolean online;
    private final Set<String> chatRoomIds = ConcurrentHashMap.newKeySet();

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
        this.online = false;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }

    public boolean isOnline() { return online; }
    public void setOnline(boolean online) { this.online = online; }

    public Set<String> getChatRoomIds() { return chatRoomIds; }
    public void joinChatRoom(String roomId) { chatRoomIds.add(roomId); }
    public void leaveChatRoom(String roomId) { chatRoomIds.remove(roomId); }
}