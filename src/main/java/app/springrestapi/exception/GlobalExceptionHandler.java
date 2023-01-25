package app.springrestapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<JsonResponse> handleNotFound(Exception e) {
        return new ResponseEntity<>(new JsonResponse(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = IllegalDataException.class)
    public ResponseEntity<JsonResponse> handleIllegalData(Exception e) {
        return new ResponseEntity<>(new JsonResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}