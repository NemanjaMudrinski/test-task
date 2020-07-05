package cyrillic_software.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cyrillic_software.dto.CustomerDto;
import cyrillic_software.models.Customer;
import cyrillic_software.services.GenerateService;

@RestController
@RequestMapping(path = "/api/customer")
public class CustomerController extends GenerateController<CustomerDto, Customer, Long> {

	public CustomerController(GenerateService<Customer, Long> service) {
		super(service, CustomerDto.class);
	}

}
