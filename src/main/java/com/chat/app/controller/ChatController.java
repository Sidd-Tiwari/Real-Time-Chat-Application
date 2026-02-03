package com.chat.app.controller;

import com.chat.app.model.ChatMessage;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {
    
    @MessageMapping("/sendMessage")//maps to /app/sendMessage
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(ChatMessage message) {
        // Logic to handle sending a chat message
        return message;
    }

    @GetMapping("/chat")
    public String chatPage() {
        return "chat"; // Returns the chat view
    }
}
