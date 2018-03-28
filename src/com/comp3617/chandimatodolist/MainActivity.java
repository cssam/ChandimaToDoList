package com.comp3617.chandimatodolist;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	private ListView taskListView;
	private String[] taskArray;
	
	public static final int REQUEST_CODE_DETAIL = 0;
	public static final int REQUEST_CODE_NEW = 1;
	public static final int REQUEST_CODE_LIST = 2;
	public static final int REQUEST_OPTIONS = 3;
	
	public static final String DETAIL = "detail";
	public static final String ARRAY_ITEM = "array_item";
	
	private TaskList taskList;
	
	private TaskDBAdapter taskDBAdapter;
	private SimpleCursorAdapter cursorAdapter;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		taskDBAdapter = new TaskDBAdapter(this);
		taskDBAdapter.open();
		
		setContentView(R.layout.activity_main);
		
		taskList  = new TaskList();
		
		SharedPreferences sp  = getSharedPreferences(com.comp3617.chandimatodolist.Options.SHARED_PREFERENCE, MODE_PRIVATE);
		boolean showCompletedValue = sp.getBoolean(com.comp3617.chandimatodolist.Options.SHOW_COMPLETED_PREFERENCE, false);
		if(showCompletedValue){
			taskArray = taskList.showCompletedTasks();
		} else {
			taskArray = taskList.showTasks();
		}
		
		
		taskListView = (ListView) findViewById(R.id.listView1);

		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, taskArray);
		taskListView.setAdapter(arrayAdapter);
		
		taskListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Task selectedTask = taskList.getTask(arg2);
				Intent i = new Intent(arg1.getContext(), TaskDetail.class);
				i.putExtra(DETAIL, selectedTask.taskDetail());
				i.putExtra(ARRAY_ITEM, arg2);
				startActivityForResult(i, REQUEST_CODE_DETAIL);
			}
			
		});
		arrayAdapter.notifyDataSetChanged();
	}
	
	private void bindListWithTask(){
		Cursor cursor = taskDBAdapter.getAllTasks();
		cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, 
				new String[] {"title", "body"},
				new int[] { android.R.id.text1, android.R.id.text2}, 0);
		setListAdapter(cursorAdapter);
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		if (requestCode == REQUEST_CODE_DETAIL)
		{
			if (resultCode == RESULT_OK)
				Toast.makeText(this, data.getDataString(), Toast.LENGTH_SHORT).show();
		}
		if(requestCode == REQUEST_CODE_NEW){
			SharedPreferences sp  = getSharedPreferences(com.comp3617.chandimatodolist.Options.SHARED_PREFERENCE, MODE_PRIVATE);
			boolean showCompletedValue = sp.getBoolean(com.comp3617.chandimatodolist.Options.SHOW_COMPLETED_PREFERENCE, false);

			if(showCompletedValue){
				taskArray = taskList.showCompletedTasks();
			} else {
				taskArray = taskList.showTasks();
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClickAddNew(View view) {
		Intent myIntent = new Intent(view.getContext(), com.comp3617.chandimatodolist.TaskNew.class);
        startActivityForResult(myIntent, REQUEST_CODE_NEW);
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		//View view = this.getCurrentFocus();
		
		Intent myIntent; 
		
		switch(item.getItemId()) {
		
		case R.id.menuList:
			myIntent = new Intent(this.getApplicationContext(), com.comp3617.chandimatodolist.MainActivity.class);
	        startActivityForResult(myIntent, REQUEST_CODE_LIST);
			
			return true;
			
		case R.id.menuNew:
			myIntent = new Intent(this.getCurrentFocus().getContext(), com.comp3617.chandimatodolist.TaskNew.class);
	        startActivityForResult(myIntent, REQUEST_CODE_NEW);
			
			return true;
			
		case R.id.menuOptions:
			myIntent = new Intent(this.getCurrentFocus().getContext(), com.comp3617.chandimatodolist.Options.class);
	        startActivityForResult(myIntent, REQUEST_OPTIONS);
			
			return true;
		
		}
		return false;
	}


}
