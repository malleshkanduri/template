package mk;

import java.util.Date;

import javax.jdo.annotations.*;
import javax.persistence.Id;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;

public class Project2 {
	@PrimaryKey @Id public Long id;

  @Index public String projectName;
  public String projectOwner;
  public Date beginDate;

   
  public Project2(String name, String owner, String id, Date date) {
	  projectName = name;
	  projectOwner = owner;
	  beginDate = date;
  }

}