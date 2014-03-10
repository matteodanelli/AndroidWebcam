package com.blogspot.cyberteo.android.webcam;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.blogspot.cyberteo.android.webcam.utils.Utils;
import com.blogspot.euroteo.webcam.R;

/**
 * Created by mattd on 3/10/14.
 */
public class HomeActivity extends Activity {

    final String WEBCAM_LIST_PAGE = "http://www.foto-webcam.eu";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        GridView webcamListView = (GridView) findViewById(R.id.webcamListView);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.webcam_list_on_loading_progress_bar);

        //Check if network is enabled
        if (!Utils.isOnline(this)) {
            Toast.makeText(this, R.string.no_connection, Toast.LENGTH_LONG).show();
            } else {
            new GetWebcamListAsyncTask(this, webcamListView, progressBar).execute(WEBCAM_LIST_PAGE);
            }
    }
}