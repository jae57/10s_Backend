package ten.shortclip.chatting.service.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ten.shortclip.chatting.exception.UserNotFoundException;
import ten.shortclip.chatting.domain.User;
import ten.shortclip.chatting.dto.RequestFriendDto;
import ten.shortclip.chatting.dto.UserProfileDto;
import ten.shortclip.chatting.repository.UserRepository;
import ten.shortclip.chatting.repository.FriendRepository;
import ten.shortclip.chatting.service.FriendService;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendServiceImpl implements FriendService {
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;

    public FriendServiceImpl(UserRepository userRepository, FriendRepository friendRepository){
        this.userRepository = userRepository;
        this.friendRepository = friendRepository;
    }

    public List<UserProfileDto> getFriendsById(Long userId){
        List<UserProfileDto> friendProfiles = new ArrayList<>();

        List<User> friends = friendRepository.getFriends(userId);
        for(User friend : friends){
            UserProfileDto userProfileDto = new UserProfileDto();

            userProfileDto.setId(friend.getId());
            userProfileDto.setNickname(friend.getNickname());
            userProfileDto.setProfileImage(friend.getProfileImage());
            userProfileDto.setStatusMessage(friend.getStatusMessage());

            friendProfiles.add(userProfileDto);
        }

        return friendProfiles;
    }

    @Transactional
    public void addFriendByEmail(Long userId, RequestFriendDto requestFriendDto){
        String friendEmail=requestFriendDto.getEmail();
        User friend = userRepository.getUserByEmail(friendEmail);
        if(friend == null) throw new UserNotFoundException("No user for this email("+friendEmail+")");

        Long friendId = friend.getId();

        friendRepository.addFriend(userId, friendId);
        friendRepository.addFriend(friendId, userId);
    }
}
