package cyrillic_software.repositories;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cyrillic_software.models.User;

@Repository
public interface UserRepository extends GenerateRepository<User, Long> {

	Optional<User> getByEmail(String email);
}
