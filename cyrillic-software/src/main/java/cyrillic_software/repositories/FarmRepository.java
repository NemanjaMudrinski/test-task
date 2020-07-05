package cyrillic_software.repositories;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cyrillic_software.models.Farm;

@Repository
public interface FarmRepository extends GenerateRepository<Farm, Long>{

	@Query("SELECT f FROM Farm f WHERE f.user.email = ?1")
	ArrayList<Farm> userHasAccessToFarm(String email);
}
