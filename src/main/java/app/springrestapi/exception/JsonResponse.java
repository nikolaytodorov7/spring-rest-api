package app.springrestapi.exception;

public class JsonResponse extends GlobalExceptionHandler {
    public String message;

    public JsonResponse(String message) {
        super();
        this.message = message;
    }
}
