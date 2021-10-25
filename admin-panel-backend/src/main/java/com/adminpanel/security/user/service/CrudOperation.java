package com.adminpanel.security.user.service;

public interface CrudOperation<T,ID> {
	
	
	T findById(ID Id);
	T save(T obj);
	

}
