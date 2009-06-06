package net.kaelog.tvshows.service.impl;

import net.kaelog.tvshows.dao.ShowDao;
import net.kaelog.tvshows.model.Show;
import net.kaelog.tvshows.service.ShowManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ShowManagerImpl extends GenericManagerImpl<Show, Long> implements ShowManager {

	private ShowDao dao ;
	
	@Autowired
	public ShowManagerImpl(ShowDao dao) {
		super(dao);
		this.dao = dao ;
	}

	public Show getShowyByName(String showName) {
		return this.dao.getShowyByName(showName) ;
	}
	
	
}
