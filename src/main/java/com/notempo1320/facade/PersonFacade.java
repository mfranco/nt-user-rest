package com.notempo1320.facade;

import com.google.common.base.Optional;
import com.notempo1320.db.GenericDAO;
import com.notempo1320.model.Person;
import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class PersonFacade implements BaseFacade<Person> {
    private GenericDAO<Person> dao;

    @Inject
    public PersonFacade(GenericDAO<Person> dao) {
        this.dao = dao;
    }

	public Person create(Person model) {
		return dao.create(model);
	}

    public Optional<Person> findById(Long id) {
        return dao.findById(id);
    }

    public List<Person> findByParams(Optional<Map<String, Object>> params) {
        return dao.findByParams(params);
    }

    public Person update(Person obj) {
        return dao.update(obj);
    }

    public void delete(Person obj) {
        dao.delete(obj);
    }

    public long count() {
        return dao.count();
    }

}
