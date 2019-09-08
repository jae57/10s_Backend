package ten.shortclip.chatting.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import ten.shortclip.chatting.model.*;
import ten.shortclip.chatting.service.*;

import java.io.*;
import java.util.*;

@RequestMapping("/api/chatroom/{id}/message")
@RestController
public class MessageController {
    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("")
    public List<Message> doGet(@PathVariable Long roomId) {
        return messageService.getAllFindByRoomId(roomId);
    }

    @PostMapping("")
    public void doPost(@PathVariable Long roomId, @RequestParam MultipartFile file) throws IOException {
        messageService.insertMessageByRoomId(roomId, file);
    }
}
