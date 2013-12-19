package com.blogspot.cyberteo.android.webcam;

import java.io.InputStream;

import uk.co.senab.photoview.PhotoViewAttacher;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

//Async Task code
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	ImageView bmImage;
    PhotoViewAttacher mAttacher;


	public DownloadImageTask(ImageView bmImage) {
		this.bmImage = bmImage;
	}

	protected Bitmap doInBackground(String... urls) {
		String urldisplay = urls[0];
		Bitmap mIcon11 = null;
		try {
			InputStream in = new java.net.URL(urldisplay).openStream();
			mIcon11 = BitmapFactory.decodeStream(in);
		} catch (Exception e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}
		return mIcon11;

	}
	
	@Override
	protected void onPostExecute(Bitmap result) {
		bmImage.setImageBitmap(result);
		mAttacher = new PhotoViewAttacher(bmImage);
	}
}