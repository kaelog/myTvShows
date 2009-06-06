package net.kaelog.tvshows.model;

import java.io.Serializable;
import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable(identityType = IdentityType.APPLICATION)
public class Episode implements Serializable {
    
	/**
	 * 
	 */
	private static final long serialVersionUID = -546952170185143007L;

	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;

    @Persistent
    private String name ;

    @Persistent
    private String description ;

    @Persistent
    private Show show ;

    @Persistent
    private Date firstBroadcast;

    @Persistent
    private Integer season ;

    @Persistent
    private Integer number ;

    public Episode() {
    }

    public Episode(Show show, String name, String description, Integer season, Integer number) {
        this.name = name;
        this.description = description;
        this.show = show;
        this.season = season;
        this.number = number;
    }

    public Key getKey() {
		return key;
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

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Date getFirstBroadcast() {
        return firstBroadcast;
    }

    public void setFirstBroadcast(Date firstBroadcast) {
        this.firstBroadcast = firstBroadcast;
    }

    public Integer getSeason() {
        return season;
    }

    public void setSeason(Integer season) {
        this.season = season;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
