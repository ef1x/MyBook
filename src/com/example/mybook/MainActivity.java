package com.example.mybook;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity{

	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		Button searchButton = (Button)findViewById( R.id.startActivity_Search );
		searchButton.setOnClickListener( new View.OnClickListener(){
			
			@Override
        	public void onClick(View v){
				Intent i = new Intent(getApplicationContext(), SearchActivity.class);
				startActivity(i);
			}
		});
			
		Button mapButton = (Button)findViewById( R.id.startActivity_map );
		mapButton.setOnClickListener( new View.OnClickListener(){
				
			@Override
			public void onClick(View v){
				Intent i = new Intent(getApplicationContext(), MapActivity.class);
				startActivity(i);
			}
		});

	}
	
	
	
}
