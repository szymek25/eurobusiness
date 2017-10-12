package pl.eurobusiness.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.eurobusiness.domain.Game;

@Repository
public interface GameDAO extends CrudRepository<Game, Integer> {

}
