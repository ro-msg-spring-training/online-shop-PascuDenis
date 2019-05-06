package ro.msg.learning.shop.repository;

import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.model.Location;

public interface ILocationRepository extends CrudRepository<Location, Integer> {
}