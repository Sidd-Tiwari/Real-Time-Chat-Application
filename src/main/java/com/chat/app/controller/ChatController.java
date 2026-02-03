package com.chat.app.controller;

import com.chat.app.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ChatController {

    @GetMapping("/")
    public String home() {
        return "redirect:/chat";
    }

    @GetMapping("/chat")
    public String chatPage() {
        return "chat";
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        return message;
    }
}
