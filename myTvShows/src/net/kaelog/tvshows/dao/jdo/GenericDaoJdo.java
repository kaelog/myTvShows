package net.kaelog.tvshows.dao.jdo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.Transactional;
import javax.persistence.NonUniqueResultException;

import net.kaelog.tvshows.dao.GenericDao;

public class GenericDaoJdo<T, PK extends Serializable> implements GenericDao<T, PK>{
	private PersistenceManager pm ;
	private Class<T> persistentClass;
	
	public GenericDaoJdo(PersistenceManager pm , Class<T> persistentClass) {
		super();
		this.pm = pm;
		this.persistentClass = persistentClass ;
	}
	
	public PersistenceManager getPersistanceManager() {
		return this.pm;
	}

	@SuppressWarnings("unchecked")
	public List<T> getAll() {
		return (List<T>) this.pm.getManagedObjects(this.persistentClass);
	}

	@SuppressWarnings("unchecked")
	public List<T> getAllDistinct() {
		Collection result = new LinkedHashSet(getAll());
        return new ArrayList(result);
	}

	@SuppressWarnings("unchecked")
	public T getObjectByParameters(Map<String, String> parameters) {
		StringBuffer buffer = new StringBuffer() ;
		for(String name : parameters.keySet()) {
			if(buffer.length() > 0) {
				buffer.append(" AND ") ;
			}
			buffer.append(name) ;
			buffer.append("==") ;
			buffer.append(name) ;
		}
		Query query = this.pm.newQuery(this.persistentClass, buffer.toString()) ;
		for(String name : parameters.keySet()) {
			query.declareParameters(name) ;
		}
		List<T> results = (List<T>) query.executeWithMap(parameters) ;
		if(results.size() == 0) {
			return null ;
		} else if(results.size() > 1) {
			throw new NonUniqueResultException() ;
		}
		return results.get(0);
	}

	@SuppressWarnings("unchecked")
	public T getObjectByParameter(String name, String value) {
		Query query = this.pm.newQuery(this.persistentClass, name+" == \""+value+"\"" ) ;
		query.setUnique(true);
		Object result = query.execute(value) ;
		
		return (T) result ;
	}

    @Transactional
	public void remove(PK id) {
		this.pm.removeUserObject(this.get(id)) ;
	}

    @Transactional
	public T save(T object) {
		return this.pm.makePersistent(object) ;
	}

    public T get(PK id) {
        return this.pm.getObjectById(this.persistentClass,id);
    }

    public boolean exists(PK id) {
        return this.get(id) != null;
    }
}
