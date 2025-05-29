package service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import model.ChatRoom;

public class ChatRoomService {
    private final ConcurrentHashMap<String, ChatRoom> chatRooms = new ConcurrentHashMap<>();
    public ChatRoom createChatRoom(String roomId, boolean isGroup, Set<String> participants) {
        ChatRoom chatRoom = new ChatRoom(roomId, isGroup, participants);
        chatRooms.put(roomId, chatRoom);
        return chatRoom;
    }
    public ChatRoom getChatRoom(String roomId) {
        return chatRooms.get(roomId);
    }
    public void addParticipant(String roomId, String userId) {
        ChatRoom chatRoom = chatRooms.get(roomId);
        if (chatRoom != null) {
            chatRoom.addParticipant(userId);
        }
    }
    public void removeParticipant(String roomId, String userId) {
        ChatRoom chatRoom = chatRooms.get(roomId);
        if (chatRoom != null) {
            chatRoom.removeParticipant(userId);
        }
    }
    
}
