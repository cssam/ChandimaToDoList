package com.comp3617.chandimatodolist;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class TaskDetail extends Activity {
	
	
	private TaskList taskList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		
		taskList  = new TaskList();
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail);
		Intent i = getIntent();
		int array_item = i.getIntExtra(com.comp3617.chandimatodolist.MainActivity.ARRAY_ITEM, 0);
		Task task = taskList.getTask(array_item);
		
		Button editButton = (Button)findViewById(R.id.button2);
		
		if(task.getStatus().equals("Completed")){
			editButton.setEnabled(false);
		} else {
			editButton.setEnabled(true);
		}
		
		
		//String[] taskDetail = i.getStringArrayExtra(com.chandima.todolist.MainActivity.DETAIL);
//		TextView uuid = (TextView)findViewById(R.id.uuid);
//		uuid.setText(taskDetail[0]);
		TextView title = (TextView)findViewById(R.id.editTitle);
		//title.setText(taskDetail[1]);
		title.setText(task.getTitle());
		
		Spinner status = (Spinner)findViewById(R.id.editStatus);
		//String statusValue = taskDetail[3];
		
		if(task.getStatus().equals("Pending")){
			status.setSelection(0);
		} else if(task.getStatus().equals("Completed")){
			status.setSelection(1);
		}
		
		Spinner category = (Spinner)findViewById(R.id.editCategory);
		//String categoryValue = taskDetail[5];
		if(task.getCategory().equals("Work")){
			category.setSelection(0);
		} else if(task.getCategory().equals("Home")){
			category.setSelection(1);
		} else if(task.getCategory().equals("School")){
			category.setSelection(2);
		} else if(task.getCategory().equals("Personal")){
			category.setSelection(3);
		}
		
		
		Spinner priority = (Spinner)findViewById(R.id.editPriority);
		//String priorityValue = taskDetail[6];
		if(task.getPriority().equals("Low")){
			priority.setSelection(0);
		} else if(task.getPriority().equals("Med")){
			priority.setSelection(1);
		} else if(task.getPriority().equals("High")){
			priority.setSelection(2);
		}
		
		
		TextView dueDate = (TextView)findViewById(R.id.editDueDate);
		dueDate.setText(task.getDateString());
		
		TextView description = (TextView)findViewById(R.id.editDescription);
		description.setText(task.getDescription());
		
		SharedPreferences sp  = getSharedPreferences(com.comp3617.chandimatodolist.Options.SHARED_PREFERENCE, MODE_PRIVATE);
		boolean emailTaskValue = sp.getBoolean(com.comp3617.chandimatodolist.Options.EMAIL_TASK_PREFERENCE, false);
		if(emailTaskValue){
			String to = "cssam1@hotmail.com";
			String subject = task.getTitle();
			String message = task.getDescription();

			Intent email = new Intent(Intent.ACTION_SEND);
			email.putExtra(Intent.EXTRA_EMAIL, new String[]{ to});

			email.putExtra(Intent.EXTRA_SUBJECT, subject);
			email.putExtra(Intent.EXTRA_TEXT, message);

			//need this to prompts email client only
			email.setType("message/rfc822");
			startActivity(Intent.createChooser(email, "Choose an Email client :"));
		}

	}
	
	public void onClickEdit(View view){
		
		int array_item = getIntent().getIntExtra(com.comp3617.chandimatodolist.MainActivity.ARRAY_ITEM, 0);
		Task task = taskList.getTask(array_item);
		
		Intent i = new Intent();
		if(task.getStatus().equals("Completed")){
			i.setData(Uri.parse("Cannot edit completed tasks"));
			setResult(RESULT_OK, i);
			finish();
		} else {
		
			EditText title = (EditText) findViewById(R.id.editTitle);
			EditText description = (EditText) findViewById(R.id.editDescription);
			Spinner category = (Spinner) findViewById(R.id.editCategory);
			EditText dueDate = (EditText) findViewById(R.id.editDueDate);
			Spinner priority = (Spinner) findViewById(R.id.editPriority);
			Spinner status = (Spinner) findViewById(R.id.editStatus);
			
			task.setAll(category.getSelectedItem().toString(), title.getText().toString(), description.getText().toString(), dueDate.getText().toString(), priority.getSelectedItem().toString(), status.getSelectedItem().toString());
			i.setData(Uri.parse("Saved edition to the list"));
			
			setResult(RESULT_OK, i);
			finish();
		}
	} 
	
	
	public void onClickViewList(View v){
		Intent i = new Intent();
		i.setData(Uri.parse("Back to the list"));
		
		setResult(RESULT_OK, i);
		finish();
	
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.task_detail, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		//View view = this.getCurrentFocus();
		
		Intent myIntent; 
		
		switch(item.getItemId()) {
		
		case R.id.menuList:
			myIntent = new Intent(this.getApplicationContext(), com.comp3617.chandimatodolist.MainActivity.class);
	        startActivityForResult(myIntent, com.comp3617.chandimatodolist.MainActivity.REQUEST_CODE_LIST);
			
			return true;
			
		case R.id.menuNew:
			myIntent = new Intent(this.getCurrentFocus().getContext(), com.comp3617.chandimatodolist.TaskNew.class);
	        startActivityForResult(myIntent, com.comp3617.chandimatodolist.MainActivity.REQUEST_CODE_NEW);
			
			return true;
			
		case R.id.menuOptions:
			myIntent = new Intent(this.getCurrentFocus().getContext(), com.comp3617.chandimatodolist.Options.class);
	        startActivityForResult(myIntent, com.comp3617.chandimatodolist.MainActivity.REQUEST_OPTIONS);
			
			return true;
		
		}
		return false;
	}

	
}
