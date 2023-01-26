package app.springrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {
    private final int NOT_FOUND_STATUS_CODE = 404;
    private final int BAD_REQUEST_STATUS_CODE = 404;
    private final String NOT_FOUND_ERROR_MESSAGE = "Not Found";
    private final String BAD_REQUEST_ERROR_MESSAGE = "Bad Request";

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<JsonResponse> handleNotFound(Exception e) {
        return new ResponseEntity<>(new JsonResponse(NOT_FOUND_STATUS_CODE, NOT_FOUND_ERROR_MESSAGE, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalDataException.class)
    public ResponseEntity<JsonResponse> handleIllegalData(Exception e) {
        return new ResponseEntity<>(new JsonResponse(BAD_REQUEST_STATUS_CODE, BAD_REQUEST_ERROR_MESSAGE, e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<JsonResponse> handleUsernameNotFound(Exception e) {
        return new ResponseEntity<>(new JsonResponse(NOT_FOUND_STATUS_CODE, NOT_FOUND_ERROR_MESSAGE, e.getMessage()), HttpStatus.NOT_FOUND);
    }
}