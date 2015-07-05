package com.vbs.custom.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotNull;

import static com.vbs.utils.Constants.DEFAULT_PHONE_NUM_VALIDATION_ERROR;

/**
 * @author DL
 * Jun 24, 2015
 */

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {PhoneNumberValidator.class})
@NotNull
@ReportAsSingleViolation
public @interface PhoneNumber {
	  String message() default DEFAULT_PHONE_NUM_VALIDATION_ERROR;

	    Class<?>[] groups() default { };

	    Class<? extends Payload>[] payload() default { };
	    
	    boolean optional();
	    
	    @Target({ ElementType.FIELD})
	    @Retention(RetentionPolicy.RUNTIME)
	    @Documented
	    @interface List {
	        String[] value();
	    }
}
