package cyrillic_software.controllers;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cyrillic_software.dto.UserDto;
import cyrillic_software.models.Account;
import cyrillic_software.models.User;
import cyrillic_software.services.GenerateService;
import cyrillic_software.services.UserService;

@RestController
@RequestMapping(path = "/api/user")
public class UserController extends GenerateController<UserDto, User, Long> {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ModelMapper modelMapper;

	public UserController(GenerateService<User, Long> service) {
		super(service, UserDto.class);
	}
	
	@RequestMapping(value="/email/{email}", method=RequestMethod.GET)
    public ResponseEntity<UserDto> findByEmail(@PathVariable String email) {
        Optional<User> t = userService.findByEmail(email);
        if(t.isPresent()) {
        	UserDto d = modelMapper.map(t.get(), UserDto.class);
            return new ResponseEntity<UserDto>(d, HttpStatus.OK);
        }
        return new ResponseEntity<UserDto>(HttpStatus.NOT_FOUND);
    }
	
	@RequestMapping(value="/edit/{email}", method=RequestMethod.PUT)
    public ResponseEntity<User> edit(@PathVariable String email, @RequestBody User user) {
		userService.update(email,user);
        return new ResponseEntity<User>(user , HttpStatus.CREATED);
    }
	
}
