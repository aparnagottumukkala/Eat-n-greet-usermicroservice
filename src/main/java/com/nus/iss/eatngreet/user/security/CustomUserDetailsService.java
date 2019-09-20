package com.nus.iss.eatngreet.user.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.nus.iss.eatngreet.user.dao.entity.UserEntity;
import com.nus.iss.eatngreet.user.dao.repository.UserRepository;
import com.nus.iss.eatngreet.user.security.model.CustomUserDetails;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {
		Optional<UserEntity> optionalUser = userRepository.findByEmailId(emailId);
		optionalUser.orElseThrow(() -> new UsernameNotFoundException("Email id not found."));
		return optionalUser.map(CustomUserDetails::new).get();
	}

}
