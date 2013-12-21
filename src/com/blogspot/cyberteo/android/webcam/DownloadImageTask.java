package com.blogspot.cyberteo.android.webcam;

import java.io.InputStream;

import uk.co.senab.photoview.PhotoViewAttacher;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

/*
 * This class download image, set into bitmap of the layout and attach animation.
 */
public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
	ImageView imageView;
    PhotoViewAttacher animation;

	public DownloadImageTask(ImageView image) {
		this.imageView = image;
	}

	protected Bitmap doInBackground(String... urls) {
		String urldisplay = urls[0];
		//width = Integer.parseInt(urls[1]);
		//height = Integer.parseInt(urls[2]);
		Bitmap bitmap = null;
		try {
			InputStream in = new java.net.URL(urldisplay).openStream();
			bitmap = BitmapFactory.decodeStream(in);
		} catch (Exception e) {
			Log.e("Error", e.getMessage());
			e.printStackTrace();
		}
		return bitmap;

	}
	
	@Override
	protected void onPostExecute(Bitmap result) {		
		//Bitmap scaledBitmap = Bitmap.createScaledBitmap(result, width, height, false);
		imageView.setImageBitmap(result);
		animation = new PhotoViewAttacher(imageView);
	}
}