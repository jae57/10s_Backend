package ten.shortclip.chatting.exception;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

//S3 연동에 문제가 있는 경우
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class AWSS3Exception extends RuntimeException {

}
