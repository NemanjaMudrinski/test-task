package cyrillic_software.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDto extends AbstractDto {

	private String email;
	private String name;
//	private List<AccountDto> account = new ArrayList<AccountDto>();
	private List<FarmDto> farm = new ArrayList<FarmDto>();
	private List<UserPermissionDto> userPermission = new ArrayList<UserPermissionDto>();
	
//	public UserDto(List<AccountDto> account) {
////		this.account = account;
//	}

	public UserDto() {
		
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<UserPermissionDto> getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(List<UserPermissionDto> userPermission) {
		this.userPermission = userPermission;
	}



	public List<FarmDto> getFarm() {
		return farm;
	}

	public void setFarm(List<FarmDto> farm) {
		this.farm = farm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
	
	

	
}
