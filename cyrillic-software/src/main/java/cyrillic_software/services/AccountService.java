package cyrillic_software.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyrillic_software.models.Account;
import cyrillic_software.repositories.AccountRepository;

@Service
public class AccountService extends GenerateService<Account, Long> {

	@Autowired
	private AccountRepository accountRepository;
	
	public ArrayList<Account> userHasAccessToAccount(String email){
		return accountRepository.userHasAccessToAccount(email);
	}
	
	@Override
	public Account edit(Long id, Account t) {
		Optional<Account> account = accountRepository.findById(id);
		if(account.isPresent()) {
			t.setId(account.get().getId());
			t.setUser(account.get().getUser());
			return accountRepository.save(t);
		}
		
		return null;
	}
	
	public Iterable<Account> getNullableAccount(){
		return accountRepository.getNullubleAccount();
	}
}
