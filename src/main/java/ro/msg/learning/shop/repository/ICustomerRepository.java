package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.model.Customer;

public interface ICustomerRepository extends CrudRepository<Customer, Integer> {

    @Query("SELECT c FROM Customer c WHERE c.username = ?1 AND c.password = ?2")
    Customer findCustomerAfterUsernameAndPassword(String username, String password);

    @Query("SELECT c FROM Customer c WHERE c.id = ?1")
    Customer findCustomerAfterId(Integer id);
}