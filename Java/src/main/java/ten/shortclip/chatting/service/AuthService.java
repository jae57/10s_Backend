package ten.shortclip.chatting.service;

import ten.shortclip.chatting.model.User;
import ten.shortclip.chatting.dto.LoginUserDto;
import ten.shortclip.chatting.dto.RequestUserDto;

public interface AuthService {
    User join(RequestUserDto requestUserDto);
    User login(LoginUserDto loginUserDto);
    User findByUserId(Long userId);
    User findByUserToken(String authToken);
}
