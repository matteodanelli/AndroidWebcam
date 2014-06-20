package com.blogspot.cyberteo.android.webcam;

import android.content.Intent;
import android.widget.*;
import uk.co.senab.photoview.PhotoViewAttacher;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageLoadingProgressListener;
import com.blogspot.euroteo.webcam.R;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Bitmap;
import android.view.Menu;
import android.view.View;
import android.view.MenuItem;

/**
 * Created by mattd on 3/10/14.
 */
public class WebcamActivity extends Activity{

    private String webcamUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webcam);
        getActionBar().setDisplayHomeAsUpEnabled(true);


        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        String webcamLink = getIntent().getStringExtra("LINK");
        webcamLink = "http://www.foto-webcam.eu".concat(webcamLink);
        //Set max resolution for screen resolution 720p
        webcamUrl = webcamLink.concat("current/1200.jpg");

        //Set title of the activity
        this.setTitle(getIntent().getStringExtra("TITLE"));

        loadImage(webcamUrl, imageView, progressBar);
    }


    /*
     * Create menu, with an action to share image
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.webcam_menu, menu);

        // Set up ShareActionProvider's default share intent
        MenuItem shareItem = menu.findItem(R.id.action_share);
        ShareActionProvider mShareActionProvider = (ShareActionProvider) shareItem.getActionProvider();
        mShareActionProvider.setShareIntent(getDefaultIntent());

        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Manage selection of option in the menu
     * @param item
     * @return
     */
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.action_refresh):
                loadImage(webcamUrl, (ImageView) findViewById(R.id.imageView), (ProgressBar) findViewById(R.id.progressBar));
                break;
        }
        return false;
    }




    // Defines a share intent to initialize the action provider.
    private Intent getDefaultIntent() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("image/*");

        //TODO: Add Image
        //Bundle extras = new Bundle();
        //final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        //imageView.buildDrawingCache();
        //extras.putParcelable("WEBCAM", imageView.getDrawingCache());
        //intent.putExtras(extras);

        return intent;
    }

    /**
     * Load image using UniversalImageLoader
     * @param url
     * @param imageView
     * @param progressBar
     */
    private void loadImage(String url, final ImageView imageView, final ProgressBar progressBar) {
        ImageLoader imageLoader = ImageLoader.getInstance();

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                //TODO
                .build();
        ImageLoader.getInstance().init(config);
        // Load image, decode it to Bitmap and return Bitmap to callback
        imageLoader.loadImage(url, null, null, new ImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                // Set bitmap and attach animation
                progressBar.setVisibility(View.GONE);
                imageView.setImageBitmap(loadedImage);
                new PhotoViewAttacher(imageView);
            }
            @Override
            public void onLoadingCancelled(String arg0, View arg1) {
                // Do nothing
            }
            @Override
            public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
                Toast.makeText(getApplicationContext(), R.string.loading_webcam_failed, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onLoadingStarted(String arg0, View arg1) {
                // Do nothing
            }
        }, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String imageUri, View view, int current, int total) {
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

}