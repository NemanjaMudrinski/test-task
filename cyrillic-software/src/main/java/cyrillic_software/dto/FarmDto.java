package cyrillic_software.dto;

public class FarmDto extends AbstractDto {

	private String farmName;
	private AccountDto account;
	
	public FarmDto() {
		
	}

	public String getFarmName() {
		return farmName;
	}

	public void setFarmName(String farmName) {
		this.farmName = farmName;
	}

	public AccountDto getAccount() {
		return account;
	}

	public void setAccount(AccountDto account) {
		this.account = account;
	}
	
}
