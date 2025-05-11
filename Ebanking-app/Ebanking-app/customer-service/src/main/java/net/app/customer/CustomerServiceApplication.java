package net.app.customer;

import net.app.customer.entites.Customer;
import net.app.customer.repositories.CustomerRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CustomerServiceApplication implements CommandLineRunner{

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Autowired
    CustomerRespository customerRespository;

    @Override
    public void run(String... args) throws Exception {
        customerRespository.save(Customer.builder().nom("User1").mail("user1@gmail.com").build());
        customerRespository.save(Customer.builder().nom("User2").mail("user2@gmail.com").build());
        customerRespository.save(Customer.builder().nom("User3").mail("user3@gmail.com").build());
    }
}
