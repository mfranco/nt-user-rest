package com.notempo1320.db.hibernate;

import com.google.common.base.Optional;
import com.notempo1320.model.Person;
import com.notempo1320.db.GenericDAO;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import org.hibernate.SessionFactory;


public class PersonHibernateDAO extends BaseHibernateDAO<Person>
    implements GenericDAO<Person>
     {

    @Inject
	public PersonHibernateDAO(SessionFactory factory) {
		super(factory);
	}

    @Override
    public Person create(Person obj){
        return persist(obj);
    }

    @Override
    public Optional<Person> findById(Long id) {
        return Optional.fromNullable(get(id));
    }


    @Override
    public Person update(Person obj) {
        return persist(obj);
    }

    @Override
    public void delete(Person obj) {
        currentSession().delete(obj);
    }
}
