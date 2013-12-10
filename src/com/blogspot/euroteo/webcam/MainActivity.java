package com.blogspot.euroteo.webcam;

import android.os.Bundle;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends Activity implements OnTouchListener {

	private static final int REFRESH = 0;
	boolean ZOOM = false;
	private static final String url = "http://www.foto-webcam.eu/webcam/bruneck/current/720.jpg";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        PhotoViewAttacher mAttacher;

		//Show The Image
		ImageView imageView = (ImageView) findViewById(R.id.imageView1);

        Drawable bitmap = getResources().getDrawable(R.drawable.wallpaper);
        mImageView.setImageDrawable(bitmap);

        // Attach a PhotoViewAttacher, which takes care of all of the zooming functionality.
        mAttacher = new PhotoViewAttacher(mImageView);

		//Download image with asyncTask /
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
