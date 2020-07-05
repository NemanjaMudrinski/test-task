package cyrillic_software.security;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cyrillic_software.models.User;
import cyrillic_software.repositories.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)throws UsernameNotFoundException {
        Optional<User> user = userRepository.getByEmail(email);
        return UserPrincipal.create(user.get());
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
    	Optional<User> user = userRepository.findById(id);
        return UserPrincipal.create(user.get());
   }
}