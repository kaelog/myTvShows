package net.kaelog.tvshows.service.impl;

import java.io.Serializable;
import java.util.List;

import net.kaelog.tvshows.dao.GenericDao;
import net.kaelog.tvshows.service.GenericManager;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public class GenericManagerImpl<T, PK extends Serializable> implements GenericManager<T, PK>{
	
	private GenericDao<T,PK> dao ;

	public GenericManagerImpl(GenericDao<T, PK> dao) {
		super();
		this.dao = dao;
	}

	public boolean exists(PK id) {
		return dao.exists(id);
	}

	public T get(PK id) {
		return dao.get(id);
	}

	public List<T> getAll() {
		return dao.getAll();
	}

	public List<T> getAllDistinct() {
		return dao.getAllDistinct();
	}

	public void remove(PK id) {
		dao.remove(id);
	}

	public T save(T object) {
		return dao.save(object);
	}

	
}
