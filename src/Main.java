import model.*;
import service.*;
import queue.MessageQueueManager;

import java.util.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        UserService userService = new UserService();
        ChatRoomService chatRoomService = new ChatRoomService();
        MessageService messageService = new MessageService();
        MessageQueueManager queueManager = MessageQueueManager.getInstance();

        // Register users
        User alice = userService.registerUser("u1", "Alice");
        User bob = userService.registerUser("u2", "Bob");
        User charlie = userService.registerUser("u3", "Charlie");

        userService.userOnline("u1");
        userService.userOnline("u2");
        userService.userOnline("u3");

        // Create a 1:1 chat room for Alice and Bob
        Set<String> participants1 = new HashSet<>(Arrays.asList(alice.getUserId(), bob.getUserId()));
        ChatRoom chatRoom1 = chatRoomService.createChatRoom("r1", false, participants1);

        // Create a group chat with all three
        Set<String> participantsGroup = new HashSet<>(Arrays.asList(alice.getUserId(), bob.getUserId(), charlie.getUserId()));
        ChatRoom groupRoom = chatRoomService.createChatRoom("r2", true, participantsGroup);

        // Alice sends message to Bob (1:1)
        Message msg1 = messageService.sendMessage(alice.getUserId(), chatRoom1.getRoomId(), "Hello Bob!", participants1);
        System.out.println("Message sent: " + msg1.getContent());

        // Bob polls his queue to get messages
        Message receivedByBob = queueManager.pollNextMessage(bob.getUserId());
        if (receivedByBob != null) {
            System.out.println("Bob received message: " + receivedByBob.getContent());
            // Bob marks message as delivered
            messageService.updateMessageStatus(receivedByBob.getMessageId(), bob.getUserId(), MessageStatus.DELIVERED);
            // Bob reads message
            messageService.updateMessageStatus(receivedByBob.getMessageId(), bob.getUserId(), MessageStatus.READ);
        }

        // Alice sends a group message
        Message groupMsg = messageService.sendMessage(alice.getUserId(), groupRoom.getRoomId(), "Hello everyone!", participantsGroup);
        System.out.println("Group message sent: " + groupMsg.getContent());

        // Charlie polls his queue
        Message receivedByCharlie = queueManager.pollNextMessage(charlie.getUserId());
        if (receivedByCharlie != null) {
            System.out.println("Charlie received group message: " + receivedByCharlie.getContent());
            messageService.updateMessageStatus(receivedByCharlie.getMessageId(), charlie.getUserId(), MessageStatus.DELIVERED);
            messageService.updateMessageStatus(receivedByCharlie.getMessageId(), charlie.getUserId(), MessageStatus.READ);
        }

        // Show message statuses for group message
        Map<String, MessageStatus> statusMap = messageService.getMessageStatus(groupMsg.getMessageId());
        System.out.println("Group message status:");
        for (Map.Entry<String, MessageStatus> entry : statusMap.entrySet()) {
            System.out.println("User " + entry.getKey() + " : " + entry.getValue());
        }
    }
}
