package cyrillic_software.repositories;

import org.springframework.stereotype.Repository;

import cyrillic_software.models.UserPermission;

@Repository
public interface UserPermissionRepository extends GenerateRepository<UserPermission, Long> {

}
