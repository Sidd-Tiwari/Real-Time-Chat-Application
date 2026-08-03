package com.chat.app.controller;

import com.chat.app.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.atomic.AtomicLong;

@Controller
public class ChatController {

    // In-memory only (no DB): last N messages, lost on restart.
    private static final int MAX_HISTORY = 100;
    private final ConcurrentLinkedDeque<ChatMessage> history = new ConcurrentLinkedDeque<>();
    private final AtomicLong idGenerator = new AtomicLong(0);

    @GetMapping("/")
    public String home() {
        return "redirect:/chat";
    }

    @GetMapping("/chat")
    public String chatPage() {
        return "chat";
    }

    // Client fetches this once on page load to backfill recent messages.
    @GetMapping("/api/history")
    @ResponseBody
    public List<ChatMessage> getHistory() {
        return List.copyOf(history);
    }

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public ChatMessage sendMessage(@Payload ChatMessage message) {
        // Basic server-side guardrails since anyone can hit the socket directly.
        String sender = message.getSender() == null ? "" : message.getSender().trim();
        String content = message.getContent() == null ? "" : message.getContent().trim();

        if (sender.isEmpty()) sender = "Anonymous";
        if (sender.length() > 40) sender = sender.substring(0, 40);
        if (content.length() > 1000) content = content.substring(0, 1000);

        ChatMessage clean = new ChatMessage(
                idGenerator.incrementAndGet(),
                sender,
                content,
                System.currentTimeMillis()
        );

        history.addLast(clean);
        while (history.size() > MAX_HISTORY) {
            history.pollFirst();
        }

        return clean;
    }

    // Broadcasts "who is typing" — nothing is stored, purely transient.
    @MessageMapping("/typing")
    @SendTo("/topic/typing")
    public String typing(@Payload String sender) {
        return sender;
    }
}
