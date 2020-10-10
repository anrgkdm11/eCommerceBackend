package tomtom.anrgkdm.ecomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tomtom.anrgkdm.ecomm.model.Item;
import tomtom.anrgkdm.ecomm.model.OrderProductPK;

@Repository
public interface ItemRepo extends JpaRepository<Item, OrderProductPK> {

}
