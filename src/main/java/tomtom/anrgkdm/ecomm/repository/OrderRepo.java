package tomtom.anrgkdm.ecomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tomtom.anrgkdm.ecomm.model.Orders;

@Repository
public interface OrderRepo extends JpaRepository<Orders, Integer> {

}
