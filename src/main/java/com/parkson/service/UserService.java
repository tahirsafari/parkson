package com.parkson.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.parkson.model.User;
import com.parkson.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(s);

        if (user.isPresent()){
            return user.get();
        }else{
            throw new UsernameNotFoundException(String.format("Username[%s] not found"));
        }
    }
    public UserDetails loadUserByUserId(Long userId) throws UsernameNotFoundException {
		return userRepository.getOne(userId);
    	
    }
    
    public void saveUser(User user) {
    	userRepository.save(user);
    }
    
    public boolean existsByUsername(String username) {
    	Optional<User> user = userRepository.findByUsername(username);
    	return user.isPresent()?true : false;
    }
}
