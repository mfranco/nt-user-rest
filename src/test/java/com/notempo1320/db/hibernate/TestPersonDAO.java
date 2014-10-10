package com.notempo1320.db;

import com.google.common.base.Optional;
import com.notempo1320.db.GenericDAO;
import com.notempo1320.db.hibernate.PersonHibernateDAO;
import com.notempo1320.model.Person;
import com.notempo1320.utils.ModelFactory;
import com.notempo1320.utils.db.TestDAO;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestPersonDAO {
    private static GenericDAO<Person> dao;
    private static SessionFactory factory;
    private static ModelFactory<Person> personFactory;

	@BeforeClass
	public static void setupClass() {
        AnnotationConfiguration config = TestDAO.getConfig();
        config.addAnnotatedClass(Person.class);
        factory = config.buildSessionFactory();
        personFactory = new ModelFactory<Person>(Person.class);
        dao = new PersonHibernateDAO(factory);
    }

    @Before
    public void beforeTest() {
        factory.getCurrentSession().beginTransaction();
    }

    @After
    public void afterTest() {
        factory.getCurrentSession().getTransaction().rollback();
    }

    @Test
    public void testCreate() throws IllegalAccessException,
        InstantiationException  {
        assertEquals(dao.count(), 0L);
        Person newPerson = personFactory.getObject();
        Person createdPerson = dao.create(newPerson);
        assertEquals(dao.count(), 1L);
    }

    @Test
    public void testUpdate() throws IllegalAccessException,
        InstantiationException {
        Person person = personFactory.getObject();
        person = dao.create(person);
        assertEquals(dao.count(), 1L);
        String updatedUsername = "updatedusername";
        person.setUsername(updatedUsername);
        person = dao.update(person);
        assertEquals(dao.count(), 1L);
        assertEquals(updatedUsername, person.getUsername());
    }

    @Test
    public void testFindById() throws IllegalAccessException,
        InstantiationException {

        Person person = personFactory.getObject();
        person = dao.create(person);
        assertEquals(dao.count(), 1L);

        Optional<Person> op = dao.findById(person.getId());
        assertTrue(op.isPresent());
        Person person2 = op.get();
        assertEquals(person.getId(), person2.getId());
    }

    @Test
    public void testFindByParams() throws IllegalAccessException,
        InstantiationException{
        Person person = null;
        for(int i=0; i<10; i++) {
            person = personFactory.getObject();
            if (i % 2 == 0) {
                person.setActive(false);
            }else{
                person.setActive(true);
            }
            dao.create(person);
        }
        Map<String, Object> params = new HashMap<>();
        Optional<Map<String, Object>> op = Optional.fromNullable(params);
        List<Person> list = dao.findByParams(op);
        assertEquals(10, list.size());

        params.put("active", true);
        op = Optional.fromNullable(params);
        list = dao.findByParams(op);
        assertEquals(5, list.size());
    }

    @Test
    public void testDelete() throws IllegalAccessException,
        InstantiationException  {
        Person newPerson = personFactory.getObject();
        Person createdPerson = dao.create(newPerson);
        assertEquals(dao.count(), 1L);
        dao.delete(createdPerson);
        assertEquals(dao.count(), 0L);
    }

}
