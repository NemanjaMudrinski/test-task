package cyrillic_software.repositories;

import org.springframework.stereotype.Repository;

import cyrillic_software.models.Customer;

@Repository
public interface CustomerRepository extends GenerateRepository<Customer, Long> {

}
