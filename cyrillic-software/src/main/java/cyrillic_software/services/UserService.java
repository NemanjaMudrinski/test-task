package cyrillic_software.services;

import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cyrillic_software.models.Account;
import cyrillic_software.models.User;
import cyrillic_software.models.UserPermission;
import cyrillic_software.repositories.AccountRepository;
import cyrillic_software.repositories.PermissionRepository;
import cyrillic_software.repositories.UserRepository;

@Service
public class UserService extends GenerateService<User, Long> {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private PermissionRepository permissionRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional
	@Override
	public User add(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		user = userRepository.save(user);
		user.setUserPermission(new HashSet<UserPermission>());
		user.getUserPermission().add(new UserPermission(null, user, permissionRepository.findById(1l).get()));
		userRepository.save(user);
		
		return super.add(user);
	}
	
	@Transactional
	public void update(String email, User t) {
		Optional<User> model = userRepository.getByEmail(email);
    	if(model.isPresent()) {
    		t.setId(model.get().getId());
    		t.setPassword(model.get().getPassword());
    		userRepository.save(t);
    	}
	}
	
	public Optional<User> findByEmail(String email) {
		return userRepository.getByEmail(email);
	}

}
