package github.gustapinto.validation;

import github.gustapinto.exception.NotNullFieldException;

public class NotBlankValidator {
    public static void validate(String field, String value) throws NotNullFieldException {
        if (value.isBlank()) {
            throw new NotNullFieldException(field);
        }
    }
}
