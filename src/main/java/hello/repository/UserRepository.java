package hello.repository;

import hello.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    User findByUsername(String username);
    boolean existsUserByUsername(String username);
    boolean existsUserByUsernameAndPassword(String username,String password);
}
