package model;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatRoom {
    private final String roomId;
    private final boolean isGroup;
    private final Set<String> participantIds = ConcurrentHashMap.newKeySet();

    public ChatRoom(String roomId, boolean isGroup, Set<String> participants) {
        this.roomId = roomId;
        this.isGroup = isGroup;
        this.participantIds.addAll(participants);
    }

    public String getRoomId() { return roomId; }
    public boolean isGroup() { return isGroup; }
    public Set<String> getParticipantIds() { return participantIds; }

    public void addParticipant(String userId) { participantIds.add(userId); }
    public void removeParticipant(String userId) { participantIds.remove(userId); }
}