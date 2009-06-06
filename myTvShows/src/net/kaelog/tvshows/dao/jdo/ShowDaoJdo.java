package net.kaelog.tvshows.dao.jdo;

import javax.jdo.PersistenceManagerFactory;

import net.kaelog.tvshows.dao.ShowDao;
import net.kaelog.tvshows.model.Show;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShowDaoJdo extends GenericDaoJdo<Show, Long> implements ShowDao {

	@Autowired
	public ShowDaoJdo(PersistenceManagerFactory pmf) {
		super(pmf.getPersistenceManager(),Show.class) ;
	}

	public Show getShowyByName(String showName) {
		return this.getObjectByParameter("name",showName) ;
	}
	
}
