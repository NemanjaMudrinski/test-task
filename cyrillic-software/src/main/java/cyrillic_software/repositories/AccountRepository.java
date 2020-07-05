package cyrillic_software.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cyrillic_software.models.Account;

@Repository
public interface AccountRepository extends GenerateRepository<Account, Long> {
	
	@Query("SELECT a FROM Account a WHERE a.user.email = ?1")
	ArrayList<Account> userHasAccessToAccount(String email);
	
	@Query("SELECT a FROM Account a WHERE a.farm = NULL")
	Iterable<Account> getNullubleAccount();
}
