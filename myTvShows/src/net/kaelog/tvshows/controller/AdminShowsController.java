package net.kaelog.tvshows.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.kaelog.tvshows.model.Show;
import net.kaelog.tvshows.service.ShowManager;
import net.kaelog.tvshows.utils.LinkExtractor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AdminShowsController {
	private static final Logger log = Logger
			.getLogger(AdminShowsController.class.getName());

	@Autowired
	private LinkExtractor extractor;

	@Autowired
	private ShowManager manager;

	public AdminShowsController() {

	}

	@RequestMapping(value = "/admin/shows/update", method = RequestMethod.GET)
	public String updateShowList(Model model) {
		try {
			log.info("Extracting shows list");
			List<String> shows = extractor.extractLinks();

			for (String showName : shows) {
				if(showName.lastIndexOf('\\') >= 0) continue ;
				log.log(Level.FINE, "managing " + showName);
				Show result = this.manager.getShowyByName(showName);
				if (result == null) {
					log.log(Level.FINE, "creating show " + showName);
					Show show = new Show(showName, "Auto Import");
					this.manager.save(show);
				}
			}
			return "admin/update";
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			e.printStackTrace();
			return "custom/500";
		}
	}

	@RequestMapping(value = "/admin/shows", method = RequestMethod.GET)
	public String showList(ModelMap model) {
		try {
			log.info("Extracting shows list");
			List<Show> result = this.manager.getAll() ;
			model.addAttribute("shows", result);
			return "admin/shows";
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage(), e);
			e.printStackTrace();
			return "custom/500";
		} 
	}
}
