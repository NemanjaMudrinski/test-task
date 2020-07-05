package cyrillic_software.repositories;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import cyrillic_software.models.Permission;

@Repository
public interface PermissionRepository extends GenerateRepository<Permission, Long> {

	Optional<Permission> getByRoleType(String roleType);
}
