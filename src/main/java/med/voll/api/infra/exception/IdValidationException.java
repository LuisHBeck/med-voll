package med.voll.api.infra.exception;

public class IdValidationException extends RuntimeException {
    public IdValidationException(String message) {
        super(message);
    }
}
