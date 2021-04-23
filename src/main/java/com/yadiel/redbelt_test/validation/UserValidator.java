package com.yadiel.redbelt_test.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.yadiel.redbelt_test.models.User;
import com.yadiel.redbelt_test.services.UserService;


@Component
public class UserValidator implements Validator {
	
	private final UserService userService;
	
	 public UserValidator(UserService userService) {
		super();
		this.userService = userService;
	}
	 
	 

	// 1
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }
    
    // 2
    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        
        if (!user.getPasswordConfirmation().equals(user.getPassword())) {
            // 3
            errors.rejectValue("passwordConfirmation", "Match");
        }         
        
//        We can talk to the service and see if any email matches the user from 
        if(this.userService.findByEmail(user.getEmail().toLowerCase()) != null) {
        	errors.rejectValue("email", "DupeEmail");
        }
        
    }

}
