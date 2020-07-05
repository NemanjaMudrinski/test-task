package cyrillic_software.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cyrillic_software.dto.AccountDto;
import cyrillic_software.models.Account;
import cyrillic_software.services.AccountService;
import cyrillic_software.services.GenerateService;

@RestController
@RequestMapping(path = "/api/account")
public class AccountController extends GenerateController<AccountDto, Account, Long> {
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private ModelMapper modelMapper;

	public AccountController(GenerateService<Account, Long> service) {
		super(service, AccountDto.class);
	}

	@RequestMapping(value="/find-account/{email}", method=RequestMethod.GET)
    public ResponseEntity<Iterable<AccountDto>> userHasAccessToAccount(@PathVariable String email) {
		List<AccountDto> d = new ArrayList<AccountDto>();
    	for(Account t : accountService.userHasAccessToAccount(email)) {
    		d.add(modelMapper.map(t, AccountDto.class));
    	}
        return new ResponseEntity<Iterable<AccountDto>>(d, HttpStatus.OK);
    }
	
	@RequestMapping(path = "/nullable", method = RequestMethod.GET)
    public ResponseEntity<Iterable<AccountDto>> getNullableAccounts() {
    	List<AccountDto> d = new ArrayList<AccountDto>();
    	for(Account t : accountService.getNullableAccount()) {
    		d.add(modelMapper.map(t, AccountDto.class));
    	} 
    	if(!d.isEmpty()) {
    		return new ResponseEntity<Iterable<AccountDto>>(d, HttpStatus.OK);
    	}
    return new ResponseEntity<Iterable<AccountDto>>(d, HttpStatus.NOT_FOUND);
    }
}
