package tomtom.anrgkdm.ecomm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tomtom.anrgkdm.ecomm.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

}
