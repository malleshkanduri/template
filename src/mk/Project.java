package mk;

import java.util.Date;

import javax.jdo.annotations.*;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class Project {
	@PrimaryKey 
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) 
	public Long id;

	@Persistent
	public String projectName;
	@Persistent
	public String projectOwner;
	@Persistent
	public Date beginDate;
	@Persistent
	public String description;
	@Persistent
	public String comment;
	
public Project(String name, String owner, Date date, String com, String desc) {
	  projectName = name;
	  projectOwner = owner;
	  beginDate = date;
	  description = desc;
	  comment = com;
}


public String getDescription() {
	return description;
}


public String getComment() {
	return comment;
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getProjectName() {
	return projectName;
}


public void setProjectName(String projectName) {
	this.projectName = projectName;
}


public String getProjectOwner() {
	return projectOwner;
}


public void setProjectOwner(String projectOwner) {
	this.projectOwner = projectOwner;
}


public Date getBeginDate() {
	return beginDate;
}


public void setBeginDate(Date beginDate) {
	this.beginDate = beginDate;
}

}