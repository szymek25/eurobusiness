package pl.eurobusiness.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.eurobusiness.domain.City;

import java.util.List;

@Repository
public interface CityDAO extends CrudRepository<City,Integer> {
    List<City> findByOwnerIsNull();
}
