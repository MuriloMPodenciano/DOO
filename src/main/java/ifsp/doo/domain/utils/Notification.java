package ifsp.doo.domain.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Notification {
    private List<Error> errors = new ArrayList<>();

    public void addError(String message) {
        addError(message, null);
    }

    public void addError(String message, Exception exception) {
        errors.add(new Error(message, exception));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public String errorMessage() {
        return errors
            .stream()
            .map(e -> e.message)
            .collect(Collectors.joining("\n"));
    }

    private class Error {
        String message;
        Exception exception;

        public Error(String message, Exception exception) {
            this.message = message;
            this.exception = exception;
        }
    }
}
