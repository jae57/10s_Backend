package ten.shortclip.chatting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ten.shortclip.chatting.domain.Chatroom;
import ten.shortclip.chatting.service.serviceImpl.ChatroomServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/chatroom")
public class ChatroomController {

    private final ChatroomServiceImpl chatroomService;

    public ChatroomController(ChatroomServiceImpl chatroomService){
        this.chatroomService = chatroomService;
    }

    @GetMapping
    public ResponseEntity<List<Chatroom>> getChatrooms(){

        List<Chatroom> rooms = new ArrayList<>();

        return ResponseEntity.status(HttpStatus.OK).body(rooms);
    }

    @PostMapping
    public ResponseEntity<Void> addChatroom(){


        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping
    public ResponseEntity<Void> updateChatroom(){

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteChatroom(){

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
