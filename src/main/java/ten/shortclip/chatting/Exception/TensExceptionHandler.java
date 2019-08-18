package ten.shortclip.chatting.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
@ControllerAdvice
public class TensExceptionHandler {

    @ExceptionHandler({UserNotFoundException.class})
    public ResponseEntity<ErrorDetail> notFound(RuntimeException re){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Timestamp(System.currentTimeMillis()));
        errorDetail.setMessage(re.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
    }

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<ErrorDetail> unAuthorized(RuntimeException re){
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setTimestamp(new Timestamp(System.currentTimeMillis()));
        errorDetail.setMessage(re.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetail);
    }


}
