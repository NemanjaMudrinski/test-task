package cyrillic_software.controllers;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cyrillic_software.models.User;
import cyrillic_software.services.LoginService;

@RestController
@RequestMapping(path = "/api/login")
public class LoginController {
	
	@Autowired
	LoginService loginService;
	
	@RequestMapping(path = "", method = RequestMethod.POST)
	public ResponseEntity<HashMap<String, String>> login(@RequestBody User user) {		
		return loginService.authenticateUser(user);
	}
}