package info.sjd.UI;

import javax.validation.ConstraintViolation;
import java.util.Set;

public class AppValidator {

    public static void validate (Object object, javax.validation.Validator validator){
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

        for (ConstraintViolation<Object> cv : constraintViolations)
            System.out.println(String.format(
                    "Внимание, ошибка! value: [%s], message: [%s]",
                    cv.getInvalidValue(), cv.getMessage()));
    }

    public static String validateString (Object object, javax.validation.Validator validator) {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

        String message ="";

        for (ConstraintViolation<Object> cv : constraintViolations) {
            message = String.format(
                    "Внимание, ошибка! value: [%s], message: [%s]",
                    cv.getInvalidValue(), cv.getMessage());
        }
        return message;

    }
}
