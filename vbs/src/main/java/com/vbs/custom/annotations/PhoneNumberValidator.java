package com.vbs.custom.annotations;

import static com.vbs.utils.Constants.PHONE_NUMBER_LENGTH_WITH_PLUS_AND_COUNTRY_CODE;
import static com.vbs.utils.Constants.PLUS;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
/**
 * @author DL
 * Jun 24, 2015
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

	private final Pattern pattern = Pattern.compile("^-?\\d+$");
	
	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(PhoneNumber constraintAnnotation) {
				
	}

	/* (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(value.length() != PHONE_NUMBER_LENGTH_WITH_PLUS_AND_COUNTRY_CODE)
			return false;
		
		if(!(PLUS==(value.charAt(0))))
			return false;
		
		return pattern.matcher(value.substring(1)).find();
	}

}
