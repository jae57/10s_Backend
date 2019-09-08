package ten.shortclip.chatting.service;

public interface JwtService {
    <T> String create(String key, T data, String subject);
    boolean isValid(String jwt);
    Long getUserId();
}
