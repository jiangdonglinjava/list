package com.mycompany.myapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		Button bu=(Button)findViewById(R.id.mainButton1);
		bu.setOnClickListener(new Button.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					Intent intent = new Intent(MainActivity.this,LoginActivity.class);
					startActivity(intent);
				}
				
			
		});

	
}
}
