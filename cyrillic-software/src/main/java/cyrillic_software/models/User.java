package cyrillic_software.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;


@Entity
public class User implements Entities<User, Long> {

	private static final long serialVersionUID = 6994050722697878088L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	@Column(nullable = false, unique = true)
	private String email;
	@Size(min = 8)
	private String password;
	
	@OneToMany(mappedBy = "user",fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	private Set<UserPermission> userPermission;
	
	@OneToMany(mappedBy = "user")
	private List<Account> account = new ArrayList<Account>();
	
	@OneToMany(mappedBy = "user")
	private Set<Farm> farm;
	
	public User() {
		
	}
	
	public User(String name, String email, @Size(min = 8) String password) {
		super();
		this.name = name;
		this.email = email;
		this.password = password;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<UserPermission> getUserPermission() {
		return userPermission;
	}

	public void setUserPermission(Set<UserPermission> userPermission) {
		this.userPermission = userPermission;
	}

	public List<Account> getAccount() {
		return account;
	}

	public void setAccount(List<Account> account) {
		this.account = account;
	}

	public Set<Farm> getFarm() {
		return farm;
	}

	public void setFarm(Set<Farm> farm) {
		this.farm = farm;
	}
	
	
	
	
}
