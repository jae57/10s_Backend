package ten.shortclip.chatting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ten.shortclip.chatting.dto.LoginUserDto;
import ten.shortclip.chatting.dto.RequestUserDto;
import ten.shortclip.chatting.domain.User;
import ten.shortclip.chatting.service.AuthService;
import ten.shortclip.chatting.service.JwtService;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    public AuthController(AuthService authService, JwtService jwtService){
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/join")
    public ResponseEntity<Void> addUser(@RequestBody RequestUserDto requestUserDto){
        // 가입시에는 DB에만 저장됨. 토큰 생성 X.
        authService.join(requestUserDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<User> getUser(){
        // 토큰 있을 때
        Long userId = jwtService.getUserId();
        User user = authService.findByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginUserDto loginUserDto, HttpServletResponse response){
        // 토큰 새로 발급받아야 할 때
        User user = authService.login(loginUserDto);
        String token = jwtService.create("user",user,"member");
        response.setHeader("Authorization", token);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }




}
