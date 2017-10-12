package pl.eurobusiness.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.eurobusiness.domain.Player;

@Repository
public interface PlayerDAO extends CrudRepository<Player , Integer> {

}
