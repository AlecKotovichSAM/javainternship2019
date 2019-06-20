/**
 * 
 */
package com.samsolutions.task1.crud;

import java.util.Collection;

/**
 * @author olegk
 *
 */
public interface ICrud<T, ID> {

	ID create(T t);
	
	T read(ID id);
	
	T update(ID id, T newData);
	
	void delete(ID id);
	
	Collection<T> findAll();
}
