package cyrillic_software.dto;

public class PermissionDto extends AbstractDto {

	private String roleType;
	
	public PermissionDto() {
		
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	
	
}
