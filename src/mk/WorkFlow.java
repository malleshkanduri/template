package mk;

import java.util.Date;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class WorkFlow {
	@PrimaryKey 
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY) 
	public Long id;

	@Persistent
	public Long projectId;
	@Persistent
	public String name;
	@Persistent
	public int no;
	@Persistent
	public int modelOpen;
	@Persistent
	public int modelClosed;
	@Persistent
	public int rsOpen;
	@Persistent
	public int rsClosed;
	@Persistent
	public int duration;
	@Persistent
	public Date dueDate;
	@Persistent
	public Date completedDate;
	@Persistent
	public int tasks;
	@Persistent
	private String mileStoneName;
	
	public WorkFlow(Long projectId, String name, int no, int modelOpen, int modelClosed, int rsOpen,
			int rsClosed, int duration, int tasks, String msname) {
		super();
		this.mileStoneName=msname;
		this.tasks=tasks;
		this.projectId = projectId;
		this.name = name;
		this.no = no;
		this.modelOpen = modelOpen;
		this.modelClosed = modelClosed;
		this.rsOpen = rsOpen;
		this.rsClosed = rsClosed;
		this.duration = duration;
		this.dueDate = new Date();
		this.completedDate = new Date();
	}
	
	public String getMileStoneName() {
		return mileStoneName;
	}

	public void setMileStoneName(String mileStoneName) {
		this.mileStoneName = mileStoneName;
	}

	public int getTasks() {
		return tasks;
	}
	public void setTasks(int tasks) {
		this.tasks = tasks;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getProjectId() {
		return projectId;
	}
	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getModelOpen() {
		return modelOpen;
	}
	public void setModelOpen(int modelOpen) {
		this.modelOpen = modelOpen;
	}
	public int getModelClosed() {
		return modelClosed;
	}
	public void setModelClosed(int modelClosed) {
		this.modelClosed = modelClosed;
	}
	public int getRsOpen() {
		return rsOpen;
	}
	public void setRsOpen(int rsOpen) {
		this.rsOpen = rsOpen;
	}
	public int getRsClosed() {
		return rsClosed;
	}
	public void setRsClosed(int rsClosed) {
		this.rsClosed = rsClosed;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public Date getDueDate() {
		return dueDate;
	}
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	public Date getCompletedDate() {
		return completedDate;
	}
	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}
	
	
	
	

}