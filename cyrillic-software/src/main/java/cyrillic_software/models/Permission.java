package cyrillic_software.models;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Permission implements Entities<Permission, Long> {

	private static final long serialVersionUID = -5233936484927152926L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String roleType;
	
	@OneToMany(mappedBy = "permission")
	private Set<UserPermission> userPermission;
	
	public Permission() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public Set<UserPermission> getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(Set<UserPermission> userPermission) {
		this.userPermission = userPermission;
	}

	
	
}
