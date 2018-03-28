package com.comp3617.chandimatodolist;

import java.util.Calendar;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class TaskNew extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.task_new, menu);
		return true;
	}
	
	public void onClickSave(View view){
		EditText title = (EditText) findViewById(R.id.editTitle);
		EditText description = (EditText) findViewById(R.id.editDescription);
		Spinner category = (Spinner) findViewById(R.id.editCategory);
		EditText dueDate = (EditText) findViewById(R.id.editDueDate);
		Spinner priority = (Spinner) findViewById(R.id.editPriority);
		Spinner status = (Spinner) findViewById(R.id.editStatus);
		
		Task task = new Task();
		task.setAll(category.getSelectedItem().toString(), title.getText().toString(), description.getText().toString(), dueDate.getText().toString(), priority.getSelectedItem().toString(), status.getSelectedItem().toString());
		
		TaskList taskList  = new TaskList();
		taskList.addTask(task);
		
		SharedPreferences sp  = getSharedPreferences(com.comp3617.chandimatodolist.Options.SHARED_PREFERENCE, MODE_PRIVATE);
		boolean promptDueValue = sp.getBoolean(com.comp3617.chandimatodolist.Options.PROMPT_DUE, false);
		
		if(promptDueValue){
			final Calendar now = Calendar.getInstance();
			String now_string = (now.get(Calendar.MONTH)+"/"+now.get(Calendar.DAY_OF_MONTH)+"/"+now.get(Calendar.YEAR));
			if(dueDate.getText().toString().equals(now_string)){
				Toast.makeText(this, "This Task Due Tomorrow.", Toast.LENGTH_LONG).show();
			}
		}
		
		Intent i = new Intent();
		i.setData(Uri.parse("Saved to the list"));
		
		setResult(RESULT_OK, i);
		finish();
	
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
