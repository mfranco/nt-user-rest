package com.notempo1320.facade;

import com.google.common.base.Optional;
import com.notempo1320.model.BaseModel;
import java.util.List;
import java.util.Map;

public interface BaseFacade<T extends BaseModel> {

    public T create(T obj);

    public Optional<T> findById(Long id);

    public List<T> findByParams(Optional<Map<String, Object>> params);

    public T update(T obj);

    public void delete(T obj);

    public long count();
}
