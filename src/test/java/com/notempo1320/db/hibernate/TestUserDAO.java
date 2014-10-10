package com.notempo1320.db;

import com.google.common.base.Optional;
import com.notempo1320.db.hibernate.UserHibernateDAO;
import com.notempo1320.db.GenericDAO;
import com.notempo1320.model.User;
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

public class TestUserDAO  {
    private static GenericDAO<User> dao;
    private static SessionFactory factory;
    private static ModelFactory<User> userFactory;

	@BeforeClass
	public static void setupClass() {
        AnnotationConfiguration config = TestDAO.getConfig();
        config.addAnnotatedClass(User.class);
        factory = config.buildSessionFactory();
        userFactory = new ModelFactory<User>(User.class);
        dao = new UserHibernateDAO(factory);
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
        User newUser = userFactory.getObject();
        User createdUser = dao.create(newUser);
        assertEquals(dao.count(), 1L);
    }

    @Test
    public void testUpdate() throws IllegalAccessException,
        InstantiationException {
        User user = userFactory.getObject();
        user = dao.create(user);
        assertEquals(dao.count(), 1L);
        String updatedUsername = "updatedusername";
        user.setUsername(updatedUsername);
        user = dao.update(user);
        assertEquals(dao.count(), 1L);
        assertEquals(updatedUsername, user.getUsername());
    }

    @Test
    public void testFindById() throws IllegalAccessException,
        InstantiationException {

        User user = userFactory.getObject();
        user = dao.create(user);
        assertEquals(dao.count(), 1L);

        Optional<User> op = dao.findById(user.getId());
        assertTrue(op.isPresent());
        User user2 = op.get();
        assertEquals(user.getId(), user2.getId());
    }

    @Test
    public void testFindByParams() throws IllegalAccessException,
        InstantiationException{
        User user = null;
        for(int i=0; i<10; i++) {
            user = userFactory.getObject();
            if (i % 2 == 0) {
                user.setActive(false);
            }else{
                user.setActive(true);
            }
            dao.create(user);
        }
        Map<String, Object> params = new HashMap<>();
        Optional<Map<String, Object>> op = Optional.fromNullable(params);
        List<User> list = dao.findByParams(op);
        assertEquals(10, list.size());

        params.put("active", true);
        op = Optional.fromNullable(params);
        list = dao.findByParams(op);
        assertEquals(5, list.size());
    }

    @Test
    public void testDelete() throws IllegalAccessException,
        InstantiationException  {
        User newUser = userFactory.getObject();
        User createdUser = dao.create(newUser);
        assertEquals(dao.count(), 1L);
        dao.delete(createdUser);
        assertEquals(dao.count(), 0L);
    }

}
