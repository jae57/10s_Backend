package ten.shortclip.chatting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ten.shortclip.chatting.dto.RequestUserProfileDto;
import ten.shortclip.chatting.dto.UserProfileDto;
import ten.shortclip.chatting.service.JwtService;
import ten.shortclip.chatting.service.serviceImpl.ProfileServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private final JwtService jwtService;
    private final ProfileServiceImpl profileService;

    public ProfileController(JwtService jwtService, ProfileServiceImpl profileService){
        this.jwtService = jwtService;
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<UserProfileDto> getMyProfile(){
        Long userId = jwtService.getUserId();
        UserProfileDto profile = profileService.getProfile(userId);
        return ResponseEntity.status(HttpStatus.OK).body(profile);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserProfileDto> getProfile(@PathVariable("user-id") Long userId){
        UserProfileDto profile = profileService.getProfile(userId);
        return ResponseEntity.status(HttpStatus.OK).body(profile);
    }

    @PutMapping
    public ResponseEntity<Void> updateProfile(@RequestBody RequestUserProfileDto requestUserProfileDto) throws IOException {
        Long userId = jwtService.getUserId();
        profileService.updateProfile(userId, requestUserProfileDto);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
