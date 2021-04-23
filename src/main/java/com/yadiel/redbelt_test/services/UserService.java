package com.yadiel.redbelt_test.services;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.yadiel.redbelt_test.models.Ideas;
import com.yadiel.redbelt_test.models.User;
import com.yadiel.redbelt_test.repositories.IdeasRepository;
import com.yadiel.redbelt_test.repositories.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final IdeasRepository ideasRepository;
    
    public UserService(UserRepository userRepository, IdeasRepository ideasRepository) {
		this.userRepository = userRepository;
		this.ideasRepository = ideasRepository;
	}

    
    // register user and hash their password
    public User registerUser(User user) {
        String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
        user.setPassword(hashed);
        user.setEmail(user.getEmail().toLowerCase());
        return userRepository.save(user);
    }
    
    // find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    // find user by id
    public User findUserById(Long id) {
    	return userRepository.findById(id).orElse(null);
    }
    
    // authenticate user
    public boolean authenticateUser(String email, String password) {
        // first find the user by email
        User user = userRepository.findByEmail(email);
        // if we can't find it by email, return false
        if(user == null) {
            return false;
        } else {
            // if the passwords match, return true, else, return false
            if(BCrypt.checkpw(password, user.getPassword())) {
                return true;
            } else {
                return false;
            }
        }
    }
    
    public Ideas createIdea(Ideas ideas) {
    	return this.ideasRepository.save(ideas);
    }
    
    public List<Ideas> getAllIdeas() {
    	return (List<Ideas>)this.ideasRepository.findAll();
    }
    
    public Ideas getIdeaById(Long id) {
    	return this.ideasRepository.findById(id).orElse(null);
    }
    
    
    public Ideas updateAnIdea(Ideas idea) {
    	return this.ideasRepository.save(idea);
    }
    
    
    public void deleteIdea(Ideas idea) {
    	this.ideasRepository.delete(idea);
    }
    
    
    
    
    
    
    
    
}
