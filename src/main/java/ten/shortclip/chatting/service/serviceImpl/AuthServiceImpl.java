package ten.shortclip.chatting.service.serviceImpl;

import org.springframework.stereotype.Service;
import ten.shortclip.chatting.exception.AlreadyExistEmailException;
import ten.shortclip.chatting.exception.WrongPasswordException;
import ten.shortclip.chatting.dto.LoginUserDto;
import ten.shortclip.chatting.dto.RequestUserDto;
import ten.shortclip.chatting.model.User;
import ten.shortclip.chatting.repository.UserRepository;
import ten.shortclip.chatting.service.AuthService;

import org.mindrot.jbcrypt.BCrypt;

@Service
public class AuthServiceImpl implements AuthService {

    private static final String PROFILE_DEFAULT_PATH = "/img/profile_default.jpg";
    private static final String EMAIL_NOT_EXIST_EXCEPTION_MSG = "등록되지 않은 이메일입니다.";

    private UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public User join(RequestUserDto requestUserDto){
        String email = requestUserDto.getEmail();
        if(isExist(email)){
            throw new AlreadyExistEmailException("This email("+email+") is already exist!");
        }

        User requestedUser = new User();
        settingForSave(requestedUser, requestUserDto);

        User createdUser = userRepository.save(requestedUser);
        return createdUser;
    }

    public User login(LoginUserDto loginUserDto){
        String email = loginUserDto.getEmail();
        String password= loginUserDto.getPassword();
        User user = userRepository.getUserByEmail(email);
        if( ! passwordChecking(user, password)){
            throw new WrongPasswordException("you have wrong password");
        }
        return user;
    }

    public User findByUserId(Long userId){
        return userRepository.getUserById(userId);
    }
    public User findByUserToken(String authToken) { return userRepository.getUserByToken(authToken); }

    private boolean isExist(String email){
        User user = userRepository.getUserByEmail(email);

        if(user == null) return false;
        return true;
    }

    private void settingForSave(User requestedUser, RequestUserDto requestUserDto){
        // 토큰 or 패스워드
        String password= requestUserDto.getPassword();
        String token = requestUserDto.getToken();

        if(password != null){
            String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
            requestedUser.setPassword(encodedPassword);
        }

        if(token != null) requestedUser.setToken(token);

        // 이메일, 닉네임
        requestedUser.setEmail(requestUserDto.getEmail());
        requestedUser.setNickname(requestUserDto.getNickname());

        // 프로필 사진
        String profileImage = requestUserDto.getProfileImage();
        if(profileImage == null) requestedUser.setProfileImage(PROFILE_DEFAULT_PATH);
        else{
            String imageUrl = "";
            requestedUser.setProfileImage(imageUrl);
        }

    }

    private boolean passwordChecking(User user,String password){
        String encodedPassword = user.getPassword();
        return BCrypt.checkpw(password, encodedPassword);
    }
}
