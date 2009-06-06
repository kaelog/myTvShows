package net.kaelog.tvshows.service;

import net.kaelog.tvshows.model.Show;

public interface ShowManager extends GenericManager<Show,Long> {
	public Show getShowyByName(String showName) ;
}
