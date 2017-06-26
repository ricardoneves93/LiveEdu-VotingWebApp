package com.sheepcow.voting.configuration;

import java.util.List;

import org.jongo.MongoCollection;
import com.sheepcow.voting.models.*;
import com.sheepcow.voting.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class MongoDBAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	
	@Autowired
    private UserRepository userRepository;

	@Override
	protected void additionalAuthenticationChecks(UserDetails arg0, UsernamePasswordAuthenticationToken arg1)
			throws AuthenticationException {
		
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		UserDetails loadedUser;

        try {
            Users user = userRepository.findUserByUsername(username).get(0);
            System.out.println(user.toString());
            //loadedUser = new User(userModel.username, userModel.password, new Granted userModel.roles);
        } catch (Exception repositoryProblem) {
            throw new InternalAuthenticationServiceException(repositoryProblem.getMessage(), repositoryProblem);
        }

        return null;
	}
	


}
