package ten.shortclip.chatting.service.serviceImpl;

import org.springframework.stereotype.Service;
import ten.shortclip.chatting.Exception.AlreadyExistEmailException;
import ten.shortclip.chatting.Exception.WrongPasswordException;
import ten.shortclip.chatting.dto.LoginUserDto;
import ten.shortclip.chatting.dto.RequestUserDto;
import ten.shortclip.chatting.domain.User;
import ten.shortclip.chatting.repository.AuthRepository;
import ten.shortclip.chatting.service.AuthService;

import org.mindrot.jbcrypt.BCrypt;

@Service
public class AuthServiceImpl implements AuthService {

    private static final String PROFILE_DEFAULT_PATH = "/img/profile_default.jpg";
    private static final String EMAIL_NOT_EXIST_EXCEPTION_MSG = "등록되지 않은 이메일입니다.";

    private AuthRepository authRepository;

    public AuthServiceImpl(AuthRepository authRepository){
        this.authRepository = authRepository;
    }

    public User join(RequestUserDto requestUserDto){
        String email = requestUserDto.getEmail();
        if(isExist(email)){
            throw new AlreadyExistEmailException("This email("+email+") is already exist!");
        }
        settingForSave(requestUserDto);
        User createdUser = authRepository.save(requestUserDto);
        return createdUser;
    }

    public User login(LoginUserDto loginUserDto){
        String email = loginUserDto.getEmail();
        String password= loginUserDto.getPassword();
        User user = authRepository.getUserByEmail(email);
        if( ! passwordChecking(user, password)){
            throw new WrongPasswordException("you have wrong password");
        }
        return user;
    }

    public User findByUserId(Long userId){
        return authRepository.getUserById(userId);
    }

    private boolean isExist(String email){
        User user = authRepository.getUserByEmail(email);

        if(user == null) return false;
        return true;
    }

    private void settingForSave(RequestUserDto requestUserDto){
        String password = requestUserDto.getPassword();
        String encodedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        requestUserDto.setPassword(encodedPassword);
        if(requestUserDto.getPassword() == null){
            requestUserDto.setProfileImage(PROFILE_DEFAULT_PATH);
        }
    }

    private boolean passwordChecking(User user,String password){
        String encodedPassword = user.getPassword();
        return BCrypt.checkpw(password, encodedPassword);
    }
}
