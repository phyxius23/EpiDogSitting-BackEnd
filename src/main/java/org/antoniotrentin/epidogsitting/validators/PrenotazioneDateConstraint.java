package org.antoniotrentin.epidogsitting.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PrenotazioneDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PrenotazioneDateConstraint {
    String message() default "La prenotazione deve essere richiesta con almeno due giorni di anticipo!";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
