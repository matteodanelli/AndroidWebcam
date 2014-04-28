package com.blogspot.cyberteo.android.webcam;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.blogspot.cyberteo.android.webcam.utils.Utils;
import com.blogspot.euroteo.webcam.R;

/**
 * Created by mattd on 3/10/14.
 */
public class HomeActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GridView webcamListView = (GridView) findViewById(R.id.webcamListView);

        //Check if network is enabled
        if (!Utils.isOnline(this)) {
            Toast.makeText(this, R.string.no_connection, Toast.LENGTH_LONG).show();
            } else {
            new GetWebcamListAsyncTask(this, webcamListView).execute();
            }
    }
}