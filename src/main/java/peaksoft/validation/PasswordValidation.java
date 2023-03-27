//package peaksoft.validation;
//
//import com.auth0.jwt.interfaces.Payload;
//import jakarta.validation.Constraint;
//
//import java.lang.annotation.*;
//
//@Target({ElementType.FIELD, ElementType.METHOD,ElementType.ANNOTATION_TYPE})
//@Documented
//@Constraint(validatedBy = PasswordValidator.class)
//@Retention(RetentionPolicy.RUNTIME)
//public @interface PasswordValidation {
//
//    String message() default "Invalid ";
//    Class<?>[]groups()default {};
//    Class<? extends Payload>[] payload() default {};
//}
