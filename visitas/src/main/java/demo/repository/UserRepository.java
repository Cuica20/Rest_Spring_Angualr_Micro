package demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import demo.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long>{

	//findByAtributo
	public User findByUserName(String username);
	
	
}
