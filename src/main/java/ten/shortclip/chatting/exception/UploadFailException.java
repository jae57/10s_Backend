package ten.shortclip.chatting.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UploadFailException extends RuntimeException {
}
