package cyrillic_software.services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyrillic_software.models.Farm;
import cyrillic_software.repositories.FarmRepository;

@Service
public class FarmService extends GenerateService<Farm, Long> {
	
	@Autowired
	private FarmRepository farmRepository;

	public ArrayList<Farm> userHasAccessToFarm(String email){
		return farmRepository.userHasAccessToFarm(email);
	}
	
	@Override
	public Farm edit(Long id, Farm t) {
		Optional<Farm> farm = farmRepository.findById(id);
		if(farm.isPresent()) {
			t.setId(farm.get().getId());
			t.setAccount(farm.get().getAccount());
			t.setUser(farm.get().getUser());
			return farmRepository.save(t);
		}
		
		return null;
	}
}
