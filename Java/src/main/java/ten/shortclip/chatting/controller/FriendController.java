package ten.shortclip.chatting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ten.shortclip.chatting.dto.RequestFriendDto;
import ten.shortclip.chatting.dto.ResponseFriends;
import ten.shortclip.chatting.dto.UserProfileDto;
import ten.shortclip.chatting.service.JwtService;
import ten.shortclip.chatting.service.serviceImpl.FriendServiceImpl;

import java.util.List;

@RestController
@RequestMapping("/api/friend")
public class FriendController {

    private final JwtService jwtService;
    private final FriendServiceImpl friendService;

    public FriendController(JwtService jwtService, FriendServiceImpl friendService) {
        this.jwtService = jwtService;
        this.friendService = friendService;
    }

    @GetMapping
    public ResponseEntity<ResponseFriends> getFriends(){
        Long userId = jwtService.getUserId();
        ResponseFriends responseFriends = new ResponseFriends();
        List<UserProfileDto> friends = friendService.getFriendsById(userId);
        responseFriends.setFriends(friends);

        return ResponseEntity.status(HttpStatus.OK).body(responseFriends);
    }

    @PostMapping
    public ResponseEntity<Void> addFriend(@RequestBody RequestFriendDto requestFriendDto){
        Long userId = jwtService.getUserId();
        friendService.addFriendByEmail(userId, requestFriendDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
