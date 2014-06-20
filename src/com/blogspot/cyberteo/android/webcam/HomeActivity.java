package com.blogspot.cyberteo.android.webcam;

import android.app.Activity;
import android.os.Bundle;
import android.widget.*;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import com.blogspot.euroteo.webcam.R;

import com.blogspot.cyberteo.android.webcam.utils.Utils;

/**
 * Created by mattd on 3/10/14.
 */
public class HomeActivity extends Activity {

    private static final String FEEDBACK_EMAIL = "bludevapps@gmail.com";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GridView webcamListView = (GridView) findViewById(R.id.webcamListView);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //Check if network is enabled
        if (!Utils.isOnline(this)) {
            Toast.makeText(this, R.string.no_connection, Toast.LENGTH_LONG).show();
            } else {
            new GetWebcamListAsyncTask(this, webcamListView, progressBar).execute();
            }
    }

    /*
     * Create menu, with an action to share image
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        GridView webcamListView = (GridView) findViewById(R.id.webcamListView);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);


        switch (item.getItemId()) {
            case (R.id.open_about_dialog):
                Utils.showAbout(this);
                return true;
            case (R.id.send_feedback):
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("plain/text");
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{FEEDBACK_EMAIL});
                intent.putExtra(Intent.EXTRA_SUBJECT, "[Foto-Webcam.eu] User feedback");
                startActivity(intent);
            case (R.id.action_refresh):
                if (!Utils.isOnline(this)) {
                    Toast.makeText(this, R.string.no_connection, Toast.LENGTH_LONG).show();
                } else {
                    new GetWebcamListAsyncTask(this, webcamListView, progressBar).execute();
                }
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
