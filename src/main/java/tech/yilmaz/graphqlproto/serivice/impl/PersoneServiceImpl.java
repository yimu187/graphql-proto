package tech.yilmaz.graphqlproto.serivice.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.yilmaz.graphqlproto.dao.PersonDao;
import tech.yilmaz.graphqlproto.model.Person;
import tech.yilmaz.graphqlproto.serivice.PersoneService;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class PersoneServiceImpl implements PersoneService {

    @Autowired
    PersonDao personDao;

    @Override
    public Person savePerson(Person person) {
        return personDao.save(person);
    }

    @Override
    public List<Person> findAll() {
        Iterable<Person> all = personDao.findAll();
        List<Person> result = new ArrayList<>();
        all.forEach(result::add);
        return result;
    }

    @Override
    public Person findByEmail(String email) {
        return personDao.findByEmail(email);
    }
}
