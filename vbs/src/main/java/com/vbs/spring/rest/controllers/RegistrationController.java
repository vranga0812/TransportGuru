package com.vbs.spring.rest.controllers;

import static com.vbs.utils.conversions.RequestToDBObjectConversion.convertUserFormToDBUserCredentialsObject;
import static com.vbs.utils.Constants.PROFILE_ACTIVATION;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.vbs.custom.annotations.RestEndpoint;
import com.vbs.custom.exceptions.AlreadyTakenException;
import com.vbs.custom.exceptions.EmailAlreadyTakenException;
import com.vbs.custom.exceptions.Error;
import com.vbs.custom.exceptions.Errors;
import com.vbs.custom.exceptions.InvalidDataException;
import com.vbs.custom.exceptions.UserNameAlreadyTakenException;
import com.vbs.custom.exceptions.VbsException;
import com.vbs.persistance.entities.Address;
import com.vbs.persistance.entities.User;
import com.vbs.persistance.entities.UserCredentials;
import com.vbs.services.IRegistrationService;
import com.vbs.ui.pojos.CreateUserRq;

/**
 * @author DL
 * Jun 20, 2015
 */

@RestEndpoint
public class RegistrationController {
	
	@Autowired
	IRegistrationService registrationService;
	
	

	/**
	 * @return the registrationService
	 */
	public IRegistrationService getRegistrationService() {
		return registrationService;
	}

	/**
	 * @param iRegistrationService the registrationService to set
	 */
	public void setRegistrationService(IRegistrationService iRegistrationService) {
		this.registrationService = iRegistrationService;
	}

	
	@RequestMapping(value="create", method=RequestMethod.POST)
	public ResponseEntity<User> createNewUser(@Valid @RequestBody CreateUserRq createUserRq, BindingResult errors, HttpServletRequest rq) throws AlreadyTakenException, InvalidDataException{
		
		System.out.println("RegistrationController.create(): "+createUserRq);
		
		if(errors.hasErrors()){
				InvalidDataException dataException = new InvalidDataException();
				dataException.setValidationError(errors);
				throw dataException;
		}
		
		UserCredentials cred = convertUserFormToDBUserCredentialsObject(createUserRq);
		
		registrationService.createNewUser(cred);
		HttpSession session = rq.getSession();
		String newSessionId = session.getId();
		rq.getSession().setAttribute("user", cred.getUser());
		System.out.println("RegistrationController, new session id is-->"+newSessionId);
		return new ResponseEntity<>(cred.getUser(),  HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/secure/activate/user/{username}/otp/{otp}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void activateUser(@PathVariable String username, @PathVariable long otp) throws VbsException{
		System.out.println("activate User");
		boolean userActivationResult = registrationService.activateUser(username, otp);
		System.out.println("userActivationResult: "+userActivationResult);
		
	}
	
	@RequestMapping(value="/secure/generateOTP/user/{username}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void requestNewOTP(@PathVariable String username) throws VbsException{
		System.out.println("requestNewOTP");
		boolean requestNewOTPStatus = registrationService.generateOtp(username, PROFILE_ACTIVATION);
		System.out.println("requestNewOTPStatus: "+requestNewOTPStatus);
		
	}
	
	@ExceptionHandler(AlreadyTakenException.class)
    @ResponseStatus(HttpStatus.IM_USED)
    @ResponseBody
	public Errors<Error> handleAlreadyTakenException(AlreadyTakenException ae){
		Errors<Error> errors = new Errors<Error>(); 
		if(ae instanceof UserNameAlreadyTakenException){
			errors.addError(new Error("username", "username is not available"));
		}else if(ae instanceof EmailAlreadyTakenException){
			errors.addError(new Error("email", "email address is not available"));
		}
		return errors;
	}
	
	@ExceptionHandler({ InvalidDataException.class })
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Errors<Error> handleBeanValidationError(InvalidDataException e)
    {
		BindingResult validationErrors = e.getValidationError();
		Errors<Error> errors = new Errors<Error>();
		for(FieldError err : validationErrors.getFieldErrors()){
			errors.addError(new Error(err.getField(),err.getDefaultMessage()));
		}
		
		
		return errors;
    }

	
	
	
	@RequestMapping(value="create", method=RequestMethod.GET)
	public ResponseEntity<CreateUserRq> dummyRequestObject(){
		
		Set<Address> addresses = new HashSet<>();
		
		Address address = new Address();
		address.setAddress1("Give the street name with house number");
		address.setAddress2("Optional address lane 2");
		address.setCity("Set the city here ");
		address.setDistrict("SetDistrict here");
		address.setState("Set Stata here");
		address.setCountry("Set the Country Here");
		address.setPincode("Set he ZipCode here");
		addresses.add(address);
		
		CreateUserRq dummyRq = new CreateUserRq();
		
		dummyRq.setFirstName("Venkata");
		dummyRq.setLastName("Ranga");
		dummyRq.setCompnayName("NONE");
		dummyRq.setNativeLanguage("Telugu");
		dummyRq.setEmail("dinesh.csit@gmail.com");
		dummyRq.setPrimaryMobile("1234567890");
		dummyRq.setSecondayMobile("44324332");
		dummyRq.setUserType("Normal");
		dummyRq.setRemarks("You Can set he remarks here");
		dummyRq.setAddress(addresses);
		dummyRq.setPassword("Sample");
		dummyRq.setUserName("Sample");
		
		return new ResponseEntity<>(dummyRq, HttpStatus.OK);
		
	}
	
	public void createDummyUserObject(){
		Set<Address> addresses = new HashSet<>();
		
		Address address = new Address();
		address.setAddressId(123);
		address.setAddress1("123 street");
		address.setCity("Vijayawada");
		address.setDistrict("Krishna");
		address.setState("AP");
		address.setCountry("India");
		address.setPincode("520001");
		address.setCreatedBy("dinesh");
		address.setCreateDate(new Date());
		address.setUpdatedBy("dinesh");
		address.setUpdatedDate(new Date());
		
		addresses.add(address);
		
		address = new Address();
		address.setAddressId(123);
		address.setAddress1("123 street");
		address.setCity("Vijayawada");
		address.setDistrict("Krishna");
		address.setState("AP");
		address.setCountry("India");
		address.setPincode("520001");
		address.setCreatedBy("dinesh");
		address.setCreateDate(new Date());
		address.setUpdatedBy("dinesh");
		address.setUpdatedDate(new Date());
		
		addresses.add(address);
		
		User user = new User();
		user.setFirstName("Venkata");
		user.setLastName("Ranga");
		user.setCompnayName("NONE");
		user.setNativeLanguage("Telugu");
		user.setEmail("dinesh.csit@gmail.com");
		user.setPrimaryMobile("1234567890");
		user.setSecondayMobile("44324332");
		user.setUserType("Normal");
		user.setAddress(addresses);
	}
	
}
