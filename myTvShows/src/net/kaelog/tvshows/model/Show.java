package net.kaelog.tvshows.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Show implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8664454941635550909L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

	@Persistent
    private String name ;

	@Persistent
    private String description ;

	@Persistent(mappedBy = "show")
    private List<Episode> episodes ;

    public Show(String name, String description) {
        this() ;
        this.name = name;
        this.description = description;
    }

    public Show() {
       this.episodes = new ArrayList<Episode>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Episode> getEpisodes() {
        return episodes;
    }

    public void setEpisodes(List<Episode> episodes) {
        this.episodes = episodes;
    }

    public Key getKey() {
        return key;
    }
}
