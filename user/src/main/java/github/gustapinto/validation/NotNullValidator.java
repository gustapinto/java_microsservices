package github.gustapinto.validation;

import github.gustapinto.exception.NotNullFieldException;

public class NotNullValidator {
    public static void validate(String field, Object value) throws NotNullFieldException {
        if (value == null) {
            throw new NotNullFieldException(field);
        }
    }
}
