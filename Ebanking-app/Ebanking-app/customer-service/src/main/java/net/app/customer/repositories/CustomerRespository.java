package net.app.customer.repositories;

import net.app.customer.entites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRespository extends JpaRepository<Customer,Integer> {
}
