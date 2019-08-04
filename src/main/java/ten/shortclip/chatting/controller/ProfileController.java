package ten.shortclip.chatting.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ten.shortclip.chatting.dto.UserProfileDto;
import ten.shortclip.chatting.service.serviceImpl.ProfileServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileServiceImpl profileService;

    public ProfileController(ProfileServiceImpl profileService){
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<UserProfileDto>> getProfiles(){
        List<UserProfileDto> profiles = new ArrayList<>();

        return ResponseEntity.status(HttpStatus.OK).body(profiles);
    }

    @GetMapping("/{user-id}")
    public ResponseEntity<UserProfileDto> getProfile(@PathVariable("user-id") Long userId){
        UserProfileDto profile = new UserProfileDto();

        return ResponseEntity.status(HttpStatus.OK).body(profile);
    }

    @PutMapping
    public ResponseEntity<Void> updateProfile(){

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
