package ten.shortclip.chatting.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ten.shortclip.chatting.model.User;
import ten.shortclip.chatting.dto.RequestUserProfileDto;
import ten.shortclip.chatting.dto.UserProfileDto;
import ten.shortclip.chatting.repository.UserRepository;
import ten.shortclip.chatting.service.AWSS3FileClient;
import ten.shortclip.chatting.service.ProfileService;

import java.io.IOException;
import java.net.URL;

@Service
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository userRepository;
    private final AWSS3FileClient awss3FileClient;

    public ProfileServiceImpl(UserRepository userRepository, AWSS3FileClient awss3FileClient){
        this.userRepository = userRepository;
        this.awss3FileClient = awss3FileClient;
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

    public void updateProfile(Long userId, RequestUserProfileDto requestUserProfileDto) throws IOException {
        User user = userRepository.getUserById(userId);
        UserProfileDto userProfile = new UserProfileDto();

        if(requestUserProfileDto.getNickname() == null) userProfile.setNickname(user.getNickname());
        if(requestUserProfileDto.getStatusMessage() == null) userProfile.setStatusMessage(user.getStatusMessage());

        MultipartFile newProfileImage = requestUserProfileDto.getProfileImage();


        if(newProfileImage == null) {
            userProfile.setProfileImage(user.getProfileImage());
        }else{
            URL imgUrl = awss3FileClient.upload(String.valueOf(userId), "10s-profile", newProfileImage.getResource().getFile());
            userProfile.setProfileImage(imgUrl.toString());
        }
        userRepository.updateUser(userId, userProfile);
    }

}
