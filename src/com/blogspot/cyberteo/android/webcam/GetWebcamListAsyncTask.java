package com.blogspot.cyberteo.android.webcam;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.blogspot.euroteo.webcam.R;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import android.widget.ProgressBar;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by mattd on 3/10/14.
 */
public class GetWebcamListAsyncTask extends AsyncTask<String, ArrayList<WebcamPreviewData>, ArrayList<WebcamPreviewData>> {

    final String WEBCAM_LIST_PAGE = "http://www.foto-webcam.eu";

    GridView webcamListView;
    Context context;
    ProgressBar progressBar;

    public GetWebcamListAsyncTask(Context context, GridView listView, ProgressBar progressBar) {
        this.webcamListView = listView;
        this.context = context;
        this.progressBar = progressBar;
    }

    protected void onProgressUpdate(Void progress) {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected ArrayList<WebcamPreviewData> doInBackground(String... urls) {

        ArrayList<WebcamPreviewData> list = new ArrayList<WebcamPreviewData>();
        String pageHTML = getInputStreamFromUrl(WEBCAM_LIST_PAGE);

        //Parse HTML and save it to list
        Document doc = Jsoup.parse(pageHTML);
        Elements elements = doc.getElementsByClass("wcovlink");

        for (Element item : elements) {
            String title = item.attr("title").toString();
            String image = item.getElementsByClass("wcovimg").first().getElementsByTag("img").first().attr("src");
            String reference = item.attr("href").toString();
            //Add only internal webcams
            if (!item.attr("target").equals("_blank")) {
                //Add absolute path
                image = "http://www.foto-webcam.eu".concat(image);
                list.add(new WebcamPreviewData(title, image, reference));
            }
        }
        return list;
    }

    protected void onPostExecute(ArrayList<WebcamPreviewData> list) {

        progressBar.setVisibility(View.INVISIBLE);

        final WebcamListAdapter adapter = new WebcamListAdapter(context, R.layout.listview_item_webcam, list);
        webcamListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                //Open activity related to that webcam
                    @Override
                    public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                        String videoUrl = adapter.getItem(position).getLink();
                        String title = adapter.getItem(position).getTitle();
                        Intent intent = new Intent(context, WebcamActivity.class);
                        intent.putExtra("LINK", videoUrl);
                        intent.putExtra("TITLE", title);
                        context.startActivity(intent);
                }
            });

        webcamListView.setAdapter(adapter);
    }


    private String getInputStreamFromUrl(String url) {
        HttpResponse response = null;
        String responseString = null;
        HttpClient httpclient = new DefaultHttpClient();
        try {
            response = httpclient.execute(new HttpGet(url));
            responseString = new BasicResponseHandler().handleResponse(response);
        } catch (ClientProtocolException e) {
            Log.e("EXCEPTION", e.getMessage());
        } catch (IOException e) {
            Log.e("EXCEPTION", e.getMessage());
        }
        return responseString;
    }

}
