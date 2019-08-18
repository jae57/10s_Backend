package ten.shortclip.chatting.service.serviceImpl;

import org.springframework.stereotype.Service;
import ten.shortclip.chatting.domain.User;
import ten.shortclip.chatting.dto.UserProfileDto;
import ten.shortclip.chatting.repository.UserRepository;
import ten.shortclip.chatting.service.ProfileService;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;

    public ProfileServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public UserProfileDto getProfile(Long userId){
        User user = userRepository.getUserById(userId);

        UserProfileDto userProfile = new UserProfileDto();
        userProfile.setId(userId);
        userProfile.setNickname(user.getNickname());
        userProfile.setStatusMessage(user.getStatusMessage());
        userProfile.setProfileImage(user.getProfileImage());

        return userProfile;
    }

    public void updateProfile(Long userId, UserProfileDto userProfileDto){
        User user = userRepository.getUserById(userId);

        if(userProfileDto.getNickname() == null) userProfileDto.setNickname(user.getNickname());
        if(userProfileDto.getProfileImage() == null) userProfileDto.setProfileImage(user.getProfileImage());
        if(userProfileDto.getStatusMessage() == null) userProfileDto.setStatusMessage(user.getStatusMessage());

        userRepository.updateUser(userId, userProfileDto);
    }

}
