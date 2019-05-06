package ro.msg.learning.shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import ro.msg.learning.shop.model.Revenue;
import org.springframework.stereotype.Repository;

//@Repository
//public interface IRevenueRepository extends JpaRepository<Revenue, Integer> {
//}

public interface IRevenueRepository extends CrudRepository<Revenue, Integer> {
}
