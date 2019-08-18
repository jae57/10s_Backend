package ten.shortclip.chatting.Exception;

public class UnauthorizedException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public UnauthorizedException(String Location) {
        super("계정 권한이 유효하지 않습니다 \n 다시 로그인 해주세요 ("+Location+")");
    }
}
