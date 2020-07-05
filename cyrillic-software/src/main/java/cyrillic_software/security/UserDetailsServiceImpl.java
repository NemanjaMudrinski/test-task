package cyrillic_software.security;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cyrillic_software.models.User;
import cyrillic_software.models.UserPermission;
import cyrillic_software.repositories.UserRepository;

@Service
public class UserDetailsServiceImpl  {
	
//	@Autowired
//	private UserRepository userRepository;
//
//	@Override
//	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//		Optional<User> user = userRepository.getByEmail(email);
//		if(user.isPresent()) {
//			ArrayList<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
//			for(UserPermission up : user.get().getUserPermission()) {
//				grantedAuthorities.add(new SimpleGrantedAuthority(up.getPermission().getRoleType()));
//			}
//			
//			return new org.springframework.security.core.userdetails.User(user.get().getEmail(), user.get().getPassword(), grantedAuthorities);
//		}
//		
//		return null;
//	}


}
