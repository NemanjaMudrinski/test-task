package cyrillic_software.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cyrillic_software.dto.FarmDto;
import cyrillic_software.models.Farm;
import cyrillic_software.services.FarmService;
import cyrillic_software.services.GenerateService;

@RestController
@RequestMapping(path = "/api/farm")
public class FarmController extends GenerateController<FarmDto, Farm, Long> {
	
	@Autowired
	private FarmService farmService;
	
	@Autowired
	private ModelMapper modelMapper;

	public FarmController(GenerateService<Farm, Long> service) {
		super(service, FarmDto.class);
	}

	@RequestMapping(value="/find-farm/{email}", method=RequestMethod.GET)
    public ResponseEntity<Iterable<FarmDto>> userHasAccessToFarm(@PathVariable String email) {
		List<FarmDto> d = new ArrayList<FarmDto>();
    	for(Farm t : farmService.userHasAccessToFarm(email)) {
    		d.add(modelMapper.map(t, FarmDto.class));
    	}
        return new ResponseEntity<Iterable<FarmDto>>(d, HttpStatus.OK);
    }
}
