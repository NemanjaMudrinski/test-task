package cyrillic_software.dto;

public class CustomerDto extends AbstractDto {

	private String name;
	private AccountDto account;
	
	public CustomerDto() {
		
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public AccountDto getAccount() {
		return account;
	}
	public void setAccount(AccountDto account) {
		this.account = account;
	}
	
	
}
