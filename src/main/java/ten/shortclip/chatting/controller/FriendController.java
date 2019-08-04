package ten.shortclip.chatting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ten.shortclip.chatting.model.User;
import ten.shortclip.chatting.service.serviceImpl.FriendServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/friend")
public class FriendController {

    private final FriendServiceImpl friendService;

    public FriendController(FriendServiceImpl friendService){
        this.friendService = friendService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getFriends(){
        List<User> friends = new ArrayList<>();

        return ResponseEntity.status(HttpStatus.OK).body(friends);
    }

    @PostMapping
    public ResponseEntity<Void> addFriend(){


        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
