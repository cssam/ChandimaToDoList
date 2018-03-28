package com.comp3617.chandimatodolist;

//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Date;
import java.util.UUID;

public class Task {
	
	private UUID id;
	private String title;
	private String description;
	private String status;
//	private Date dueDate;
	private String dateString;
	private String category;
	private String priority;
	
	
	public void setAll(String category, String title, String description, String date, String priority, String status){
		setCategory(category);
		setDescription(description);
		setId(UUID.randomUUID());
		setPriority(priority);
		setTitle(title);
		setStatus(status);
		setDateString(date);
		
//		try {
//			Date aDate = new SimpleDateFormat("MM/dd/yyyy").parse(date);
//			
//			setDueDate(aDate);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
	
	public UUID getId() {
		return id;
	}
	public void setId(UUID id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
//	public Date getDueDate() {
//		return dueDate;
//	}
//	public void setDueDate(Date dueDate) {
//		this.dueDate = dueDate;
//	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	
	
	public String taskAllFileds(){
		 StringBuffer show = new StringBuffer();
		 show.append(this.title);
		 show.append(" - ");
		 show.append(this.status);
		 show.append(" - ");
		 show.append(this.dateString);
		 show.append(" - ");
		 show.append(this.category);
		 show.append(" - ");
		 show.append(this.priority);
		 show.append(" - ");
		 show.append(this.description);
		 return show.toString();
	}
	
	public String taskBrief(){
	
		 StringBuffer show = new StringBuffer();
		 show.append(this.title);
		 show.append(" - ");
		 show.append(this.status);
		 show.append(" - ");
		 show.append(this.dateString);
//		 show.append(this.dueDate);
		
		return show.toString();
	}
	
	public String[] taskDetail(){
		
		 String[] taskDetailArray = new String[7];
		 taskDetailArray[0] = id.toString();
		 taskDetailArray[1] = title;
		 taskDetailArray[2] = description;
		 taskDetailArray[3] = status;
//		 taskDetailArray[4] = ""+dueDate;
		 taskDetailArray[4] = dateString;
		 taskDetailArray[5] = category;
		 taskDetailArray[6] = priority;
		
		return taskDetailArray;
	}

}
