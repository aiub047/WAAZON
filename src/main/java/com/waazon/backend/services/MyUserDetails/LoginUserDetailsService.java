package com.waazon.backend.services.MyUserDetails;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.waazon.backend.domains.User;
import com.waazon.backend.repositories.UserRepo;

@EnableWebSecurity
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
    UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userRepo.findUserByUsername(username);
		user.orElseThrow(() -> new UsernameNotFoundException("USER NOT FOUND"));

		return new LoginUserDetails(user.get());
	}
}
