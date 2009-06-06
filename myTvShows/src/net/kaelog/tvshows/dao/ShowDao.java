package net.kaelog.tvshows.dao;

import net.kaelog.tvshows.model.Show;

public interface ShowDao extends GenericDao<Show, Long>{

	public Show getShowyByName(String showName);

}
