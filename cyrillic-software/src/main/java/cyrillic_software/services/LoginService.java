package cyrillic_software.services;

import java.util.HashMap;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import cyrillic_software.models.User;
import cyrillic_software.utils.TokenProvider;

@Service
public class LoginService {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenProvider tokenProvider;

	public ResponseEntity<HashMap<String, String>> authenticateUser(@Valid @RequestBody User loginRequest) {

		try {
			Authentication authentication = authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
	
	        SecurityContextHolder.getContext().setAuthentication(authentication);

	        String token = tokenProvider.createToken(authentication);
	        
	        HashMap<String, String> data = new HashMap<String, String>();
	        
			data.put("token", token);
			
	        return new ResponseEntity<HashMap<String, String>>(data, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<HashMap<String, String>>(HttpStatus.UNAUTHORIZED);
		}
	}
}