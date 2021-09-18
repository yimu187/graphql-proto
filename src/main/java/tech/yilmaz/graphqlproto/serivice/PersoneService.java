package tech.yilmaz.graphqlproto.serivice;

import tech.yilmaz.graphqlproto.model.Person;

import java.util.List;

public interface PersoneService {
    Person savePerson(Person person);

    List<Person> findAll();

    Person findByEmail(String email);
}
