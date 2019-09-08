package ten.shortclip.chatting.service;

import ten.shortclip.chatting.dto.RequestUserProfileDto;
import ten.shortclip.chatting.dto.UserProfileDto;

import java.io.IOException;

public interface ProfileService {
    UserProfileDto getProfile(Long userId);
    void updateProfile(Long userId, RequestUserProfileDto requestUserProfileDto) throws IOException;
}
