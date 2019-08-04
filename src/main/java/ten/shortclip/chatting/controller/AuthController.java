package ten.shortclip.chatting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ten.shortclip.chatting.model.User;
import ten.shortclip.chatting.service.serviceImpl.AuthServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthServiceImpl authService;

    public AuthController(AuthServiceImpl authService){
        this.authService = authService;
    }

    @GetMapping
    public ResponseEntity<User> getUser(){

        User user = new User();

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping
    public ResponseEntity<Void> addUser(){



        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
