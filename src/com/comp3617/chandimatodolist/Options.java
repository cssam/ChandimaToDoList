package com.comp3617.chandimatodolist;

import java.io.FileOutputStream;

import android.app.Activity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.content.Intent;
//import android.widget.Toast;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Options extends Activity {

	public static final String SHARED_PREFERENCE = "com.chandima.todolist.options.prefs";
	public static final String SHOW_COMPLETED_PREFERENCE = "showCompleted";
	public static final String EMAIL_TASK_PREFERENCE = "emailTask";
	public static final String PROMPT_DUE = "promptDue";
	
	private static final String FILE_NAME = "my_tasks_ile";
	private static final String APP_TAG = "ToDoList";
	
	public Options() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		
		SharedPreferences sp  = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
		
		RadioButton showCompletedNo = (RadioButton) findViewById(R.id.radioShowCompletedNo);
		RadioButton showCompletedYes = (RadioButton) findViewById(R.id.radioShowCompletedYes);
		boolean showCompletedValue = sp.getBoolean(SHOW_COMPLETED_PREFERENCE, false);
		//Toast.makeText(this, "showCompletedValue: "+showCompletedValue+".", Toast.LENGTH_LONG).show();
		if(showCompletedValue){
			showCompletedNo.setChecked(false);
			showCompletedYes.setChecked(true);
		} else {
			showCompletedNo.setChecked(true);
			showCompletedYes.setChecked(false);
		}
		
		RadioButton emailTaskNo = (RadioButton) findViewById(R.id.radioEmailTaskNo);
		RadioButton emailTaskYes = (RadioButton) findViewById(R.id.radioEmailTaskYes);
		boolean emailTaskValue = sp.getBoolean(EMAIL_TASK_PREFERENCE, false);
		if(emailTaskValue){
			emailTaskNo.setChecked(false);
			emailTaskYes.setChecked(true);
		} else {
			emailTaskNo.setChecked(true);
			emailTaskYes.setChecked(false);
		}
		
		RadioButton promptDueNo = (RadioButton) findViewById(R.id.radioPromptDueNo);
		RadioButton promptDueYes = (RadioButton) findViewById(R.id.radioPromptDueYes);
		boolean promptDueValue = sp.getBoolean(PROMPT_DUE, false);
		if(promptDueValue){
			promptDueNo.setChecked(false);
			promptDueYes.setChecked(true);
		} else {
			promptDueNo.setChecked(true);
			promptDueYes.setChecked(false);
		}
		
		
	}
	

	public void onClickedShowCompleted(View view) {
		
		SharedPreferences sp  = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
		Editor editor = sp.edit();
		
		boolean checked = ((RadioButton) view).isChecked();
		switch(view.getId()){
			
			case R.id.radioShowCompletedNo:
				if(checked)
					editor.putBoolean(SHOW_COMPLETED_PREFERENCE, false);
					break;
					
			case R.id.radioShowCompletedYes:
				if(checked)
					editor.putBoolean(SHOW_COMPLETED_PREFERENCE, true);
					break;
		}
		editor.commit();
		
	}
	
	public void onClickedEmailTask(View view) {
		
		SharedPreferences sp  = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
		Editor editor = sp.edit();
		
		boolean checked = ((RadioButton) view).isChecked();
		switch(view.getId()){
			case R.id.radioEmailTaskNo:
				if(checked)
					editor.putBoolean(EMAIL_TASK_PREFERENCE, false);
					break;
					
			case R.id.radioEmailTaskYes:
				if(checked)
					editor.putBoolean(EMAIL_TASK_PREFERENCE, true);
					break;					
		}
		editor.commit();
		
	}
	
	public void onClickedPromptDue(View view) {
		
		SharedPreferences sp  = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
		Editor editor = sp.edit();
		
		boolean checked = ((RadioButton) view).isChecked();
		switch(view.getId()){

			case R.id.radioPromptDueNo:
				if(checked)
					editor.putBoolean(PROMPT_DUE, false);
					break;
					
			case R.id.radioPromptDueYes:
				if(checked)
					editor.putBoolean(PROMPT_DUE, true);
					break;
		}
		editor.commit();
		
	}
	
	public void onClickSaveFile(View view){
		
		
		TaskList tl = new TaskList();
		String data = tl.tasksInBuffer();
		
		try {
			FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
			fos.write(data.getBytes());
			fos.close();
			
			
		} catch (Exception e) {
			Log.e(APP_TAG, "Something Wrong on write", e);
		}
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options, menu);
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
