package com.blogspot.cyberteo.android.webcam;

import com.blogspot.euroteo.webcam.R;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.view.MenuItem;

public class MainActivity extends Activity{

	private static final int REFRESH = 0;
	private static final String url = "http://www.foto-webcam.eu/webcam/bruneck/current/1200.jpg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Download image with asyncTask, set image with photoViewAttacher
		new DownloadImageTask((ImageView) findViewById(R.id.imageView1)).execute(url);
		
	}


	/*
	 * Menu
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		menu.add(0, REFRESH, 0, "Refresh");
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case (REFRESH):
			Log.d("tag", "refresh");
			new DownloadImageTask((ImageView) findViewById(R.id.imageView1)).execute(url);
			break;
		}
		return false;
	}

}

