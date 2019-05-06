package ro.msg.learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.model.Address;

public interface IAddressRepository extends CrudRepository<Address, Integer> {
}

