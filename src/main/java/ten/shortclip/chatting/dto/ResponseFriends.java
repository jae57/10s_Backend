package ten.shortclip.chatting.dto;


import java.util.List;

public class ResponseFriends {

    private List<UserProfileDto> friend;

    public List<UserProfileDto> getFriends() {
        return friend;
    }

    public void setFriends(List<UserProfileDto> friends) {
        this.friend = friends;
    }
}
