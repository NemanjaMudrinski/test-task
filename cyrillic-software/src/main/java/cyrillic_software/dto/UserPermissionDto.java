package cyrillic_software.dto;


public class UserPermissionDto extends AbstractDto {

	private PermissionDto permission;
	
	public UserPermissionDto() {
		
	}

	public PermissionDto getPermission() {
		return permission;
	}

	public void setPermission(PermissionDto permission) {
		this.permission = permission;
	}
	
	
}
