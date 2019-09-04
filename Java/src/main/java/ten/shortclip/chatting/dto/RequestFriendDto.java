package ten.shortclip.chatting.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestFriendDto {

    @JsonProperty("friend_email")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "RequestFriendDto{" +
                "email='" + email + '\'' +
                '}';
    }
}

