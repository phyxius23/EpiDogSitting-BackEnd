package org.antoniotrentin.epidogsitting.validators;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class PrenotazioneDateValidator implements ConstraintValidator<PrenotazioneDateConstraint, LocalDate> {

    public void initialize(PrenotazioneDateValidator prenotazioneDate) {
    }

    @Override
    public boolean isValid(LocalDate prenotazioneDate, ConstraintValidatorContext cxt) {
        LocalDate validDate = LocalDate.now().plusDays(2);
        return prenotazioneDate.isAfter(validDate) || prenotazioneDate.isEqual(validDate);
    }
}
