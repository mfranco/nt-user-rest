package com.notempo1320.facade;

import com.google.common.base.Optional;
import com.notempo1320.db.GenericDAO;
import com.notempo1320.model.User;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class UserFacade implements BaseFacade<User> {
    private GenericDAO<User> dao;

    @Inject
    public UserFacade(GenericDAO<User> dao) {
        this.dao = dao;
    }

	public User create(User model) {
		return dao.create(model);
	}

    public Optional<User> findById(Long id) {
        return dao.findById(id);
    }

    public List<User> findByParams(Optional<Map<String, Object>> params) {
        return dao.findByParams(params);
    }

    public User update(User obj) {
        return dao.update(obj);
    }

    public void delete(User obj) {
        dao.delete(obj);
    }

    public long count() {
        return dao.count();
    }

}
