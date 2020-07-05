package cyrillic_software.services;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import cyrillic_software.models.Entities;
import cyrillic_software.repositories.GenerateRepository;

@Service
public abstract class GenerateService<T extends Entities<T, ID>, ID extends Serializable> {

    @Autowired
    protected GenerateRepository<T, ID> repository;
    
    public GenerateRepository<T, ID> getRepository() {
    	return this.repository;
    }

    public void setRepository(GenerateRepository<T, ID> repository) {
    	this.repository = repository;
    }

    public Page<T> findAll(Pageable pageable) {
    	return this.repository.findAll(pageable);
    }

    public Iterable<T> findAll() {
    	return this.repository.findAll();
    }

    public Optional<T> findById(ID id) {
    	return this.repository.findById(id);
    }

    public void save(T t) {
    	this.repository.save(t);
    }
    
    public T add(T t) {
    	return this.repository.save(t);
    }

    public void delete(ID id) {
    	Optional<T> t = this.repository.findById(id);
    	if(t.isPresent()) {
    		this.repository.delete(t.get());
    	}
    }

    public T edit(ID id, T t) {
    	Optional<T> model = this.repository.findById(id);
    	if(model.isPresent()) {
    		t.setId(model.get().getId());
    		return this.repository.save(t);
    	}
    	return null;
    }

}