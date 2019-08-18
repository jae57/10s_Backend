package ten.shortclip.chatting.Exception;

public class AlreadyExistEmailException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    public AlreadyExistEmailException(String message) {
        super(message);
    }
}
