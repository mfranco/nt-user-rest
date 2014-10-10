package com.notempo1320.db.hibernate;

import com.google.common.base.Optional;
import com.notempo1320.db.GenericDAO;
import com.notempo1320.model.User;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;
import org.hibernate.SessionFactory;


public class UserHibernateDAO extends BaseHibernateDAO<User>
    implements GenericDAO<User>
    {

    @Inject
	public UserHibernateDAO(SessionFactory factory) {
		super(factory);
	}

    @Override
    public User create(User obj){
        return persist(obj);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.fromNullable(get(id));
    }

    @Override
    public User update(User obj) {
        return persist(obj);
    }

    @Override
    public void delete(User obj) {
        currentSession().delete(obj);
    }
}


