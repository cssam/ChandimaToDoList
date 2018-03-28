package com.comp3617.chandimatodolist;

import java.util.ArrayList;
import java.util.List;


public class TaskList {
	
	private ArrayList<Task> taskArray;

	public TaskList() {
		// TODO Auto-generated constructor stub
	}
	public void addTask(Task task) {
		taskArray.add(task);
	}
	
	public Task getTask(int id){
		return taskArray.get(id);
	}
	
	public List<Task> getAllTasks(){

		return taskArray;
	}
	
	public String[] showTasks(){
		int s = taskArray.size();
		String[] showList = new String[s];
		
		for(int i=0; i<s; i++){
			Task task = taskArray.get(i);
			showList[i] = task.taskBrief();
		}
		
		return showList;
	}
	
	public String[] showCompletedTasks(){
		int s = taskArray.size();
		ArrayList<String> showList = new ArrayList<String>();
		
		for(int i=0; i<s; i++){
			Task task = taskArray.get(i);
			if(task.getStatus().equals("Completed")){
				showList.add(task.taskBrief());
			} 
		}
		int ns = showList.size();
		String[] result = new String[ns];
		for(int j=0; j<ns; j++){
			result[j] = showList.get(j);
		}
		
		
		return result;
	}
	
	public String tasksInBuffer(){
		int s = taskArray.size();
		StringBuffer taskbuffer = new StringBuffer();
		for(int i=0; i<s; i++){
			Task task = taskArray.get(i);
			taskbuffer.append(task.taskAllFileds());
		}
		
		return taskbuffer.toString();
	}
}
