package ten.shortclip.chatting.service;

import ten.shortclip.chatting.dto.UserProfileDto;

public interface ProfileService {
    UserProfileDto getProfile(Long userId);
    void updateProfile(Long userId, UserProfileDto userProfileDto);
}
