package tech.yilmaz.graphqlproto.dao;

import org.springframework.data.repository.CrudRepository;
import tech.yilmaz.graphqlproto.model.Person;

public interface PersonDao extends CrudRepository<Person, Long> {
    Person findByEmail(String email);
}
