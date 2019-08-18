package ten.shortclip.chatting.service;

import ten.shortclip.chatting.dto.RequestFriendDto;
import ten.shortclip.chatting.dto.UserProfileDto;

import java.util.List;

public interface FriendService {
    List<UserProfileDto> getFriendsById(Long userId);
    void addFriendByEmail(Long userId, RequestFriendDto requestFriendDto);
}
