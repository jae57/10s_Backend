package ten.shortclip.chatting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserProfileDto {
    private Long id;
    private String nickname;
    @JsonProperty("profile_image")
    private String profileImage;
    @JsonProperty("status_message")
    private String statusMessage;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }


}
