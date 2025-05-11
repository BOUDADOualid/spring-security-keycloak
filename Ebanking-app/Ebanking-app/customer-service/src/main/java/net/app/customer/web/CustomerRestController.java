package net.app.customer.web;

import net.app.customer.entites.Customer;
import net.app.customer.repositories.CustomerRespository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("v1/api/customers")
public class CustomerRestController {

    private CustomerRespository customerRespository;

    public CustomerRestController(CustomerRespository customerRespository) {
        this.customerRespository = customerRespository;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return this.customerRespository.findAll();
    }

    @GetMapping("/{id}")
    public Customer getCustomerById(@PathVariable int id) {
        return this.customerRespository.findById(id).get();
    }


}

