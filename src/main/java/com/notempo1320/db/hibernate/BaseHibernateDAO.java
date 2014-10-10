package com.notempo1320.db.hibernate;

import com.google.common.base.Optional;
import com.notempo1320.model.BaseModel;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;
import org.hibernate.Criteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.SessionFactory;


abstract public class BaseHibernateDAO<T> extends AbstractDAO <T> {

    public BaseHibernateDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
	}

    public abstract T create(T obj);

    public abstract Optional<T> findById(Long id);

    public List<T> findByParams(
        Optional<Map<String, Object>> params) {
        Criteria criteria = criteria();

        if(params.isPresent()) {

            Map<String, Object> mapParams = params.get();
            for (Map.Entry<String, Object> entry: mapParams.entrySet()) {
                criteria.add(
                    Restrictions.eq(entry.getKey(), entry.getValue()));
            }
        }

        return criteria.list();
    }

    public abstract T update(T obj);

    public abstract void delete(T obj);

    public long count() {
        return (Long) criteria().
            setProjection(Projections.rowCount()).uniqueResult();
    }

}
