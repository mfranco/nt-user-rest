package com.notempo1320.utils;

import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;


/**
 * Generic factory for create entities by demand for unit tests
 * @param <E>
 */

public class ModelFactory <E>{

	Class theClass = null;

    public ModelFactory(Class theClass) {
        this.theClass = theClass;
    }

    public E createInstance()
    throws IllegalAccessException, InstantiationException {
        return (E) this.theClass.newInstance();
    }


	public E getObject() throws IllegalAccessException, InstantiationException {
		E obj = (E) this.theClass.newInstance();
		PodamFactory factory = new PodamFactoryImpl();
		return factory.manufacturePojo((Class<E>) obj.getClass());
	}

}
