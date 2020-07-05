package cyrillic_software.repositories;

import java.io.Serializable;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import cyrillic_software.models.Entities;

@NoRepositoryBean
public interface GenerateRepository<T extends Entities<T, ID>, ID extends Serializable> extends PagingAndSortingRepository<T, ID>{

   
  
}