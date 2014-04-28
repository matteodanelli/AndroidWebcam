package com.blogspot.cyberteo.android.webcam;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.blogspot.euroteo.webcam.R;
import com.koushikdutta.urlimageviewhelper.UrlImageViewHelper;
import com.squareup.picasso.Picasso;


import java.util.List;

/**
 * Created by mattd on 3/10/14.
 */
public class WebcamListAdapter extends ArrayAdapter<WebcamPreviewData> {

    public WebcamListAdapter (Context context, int itemView, List<WebcamPreviewData> values) {
        super(context, itemView, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.listview_item_webcam, parent, false);

        TextView title = (TextView) rowView.findViewById(R.id.title);
        ImageView thumbnail = (ImageView) rowView.findViewById(R.id.preview);

        title.setText(getItem(position).getTitle());
        //Cache image for 5 minutes --> NO MORE APPLICABLE
        Picasso.with(parent.getContext())
                .load(getItem(position).getThumb())
                .into(thumbnail);
        //UrlImageViewHelper.setUrlDrawable(thumbnail, getItem(position).getThumb(), null, 5000);
        //String reference = getItem(position).getLink();

        return rowView;
    }
}