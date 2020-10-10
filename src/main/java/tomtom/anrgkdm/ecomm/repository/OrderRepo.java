package tomtom.anrgkdm.ecomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tomtom.anrgkdm.ecomm.model.Item;
import tomtom.anrgkdm.ecomm.model.Order;
import tomtom.anrgkdm.ecomm.model.OrderProductPK;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {

}
