package org.tlabs.pma.validator;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy=ProjectDateValidator.class)
public @interface ValidDate {
	
	String message() default "Invalid Date Entered!";
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
	

}
