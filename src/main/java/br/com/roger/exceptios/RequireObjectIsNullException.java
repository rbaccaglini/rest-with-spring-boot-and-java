package br.com.roger.exceptios;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequireObjectIsNullException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public RequireObjectIsNullException(String message) {
        super(message);
    }

    public RequireObjectIsNullException() {
        super("It is not allowed to persist a null objects!");
    }
}
