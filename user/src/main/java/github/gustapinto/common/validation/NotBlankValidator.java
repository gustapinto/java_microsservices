package github.gustapinto.common.validation;

import github.gustapinto.common.exception.NotNullFieldException;

public class NotBlankValidator {
    public static void validate(String field, String value) throws NotNullFieldException {
        if (value.isBlank()) {
            throw new NotNullFieldException(field);
        }
    }
}
