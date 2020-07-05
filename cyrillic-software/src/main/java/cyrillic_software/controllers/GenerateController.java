package cyrillic_software.controllers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cyrillic_software.dto.AbstractDto;
import cyrillic_software.models.Entities;
import cyrillic_software.services.GenerateService;


public abstract class GenerateController<D extends AbstractDto, T extends Entities<T, ID>, ID extends Serializable> {

    @Autowired
    protected GenerateService<T, ID> service;
    
    private Class<D> dto;
    
    @Autowired
    private ModelMapper modelMapper;
    
    public GenerateService<T, ID> getService() {
    	return this.service;
    }
    
    public GenerateController(GenerateService<T, ID> service,Class<D> dto){
    	this.dto = dto;
    	this.service = service;
    }
  

    public void setService(GenerateService<T, ID> service) {
	this.service = service;
    }

//	@RequestMapping(value="/all", method=RequestMethod.GET)
//    public ResponseEntity<Page<D>> findPage(@PageableDefault(value=10) Pageable pageable) {
//        return new ResponseEntity<Page<D>>(service.findAll(pageable).map(c -> D), HttpStatus.OK);
//    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public ResponseEntity<Iterable<D>> findAll() {
    	List<D> d = new ArrayList<D>();
    	for(T t : service.findAll()) {
    		d.add(modelMapper.map(t, this.dto));
    	} 
    	if(!d.isEmpty()) {
    		return new ResponseEntity<Iterable<D>>(d, HttpStatus.OK);
    	}
    return new ResponseEntity<Iterable<D>>(d, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public ResponseEntity<D> findById(@PathVariable ID id) {
        Optional<T> t = service.findById(id);
        if(t.isPresent()) {
        	D d = modelMapper.map(t.get(), dto);
            return new ResponseEntity<D>(d, HttpStatus.OK);
        }
        return new ResponseEntity<D>(HttpStatus.NOT_FOUND);
    }

	@RequestMapping(value="/save", method=RequestMethod.POST)
    public ResponseEntity<T> save(@RequestBody T t) {
		service.save(t);
        return new ResponseEntity<T>(t, HttpStatus.CREATED);
    }
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
    public ResponseEntity<D> add(@RequestBody T t) {
		service.add(t);
        return new ResponseEntity<D>(modelMapper.map(t, dto), HttpStatus.CREATED);
    }

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public ResponseEntity<D> edit(@PathVariable ID id, @RequestBody T t) {
		service.edit(id, t);
        return new ResponseEntity<D>(modelMapper.map(t, dto), HttpStatus.CREATED);
    }

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<T> delete(@PathVariable ID id) {
        try {
        	service.delete(id);
        }catch (Exception e) {
            return new ResponseEntity<T>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<T>(HttpStatus.NO_CONTENT);
    }

}