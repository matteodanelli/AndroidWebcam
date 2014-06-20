package com.blogspot.cyberteo.android.webcam.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import com.blogspot.euroteo.webcam.R;
/**
 * Created by mattd on 3/10/14.
 */
public class Utils {

    //Check if smartphone is connected to a network
    public static boolean isOnline(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }
        return false;
    }

    public static void showAbout(Activity activity) {

        FragmentManager fm = activity.getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment prev = fm.findFragmentByTag("about_dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        new AboutDialog().show(ft,"about_dialog");
    }

    public static class AboutDialog extends DialogFragment {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            LayoutInflater layoutInflater = getActivity().getLayoutInflater();
            View rootView = layoutInflater.inflate(R.layout.about_dialog, null);
            TextView appName = (TextView) rootView.findViewById(R.id.app_name);
            appName.setText("Foto-Webcam.eu");

            TextView aboutBodyView = (TextView) rootView.findViewById(R.id.about_body);
            aboutBodyView.setText(Html.fromHtml(getString(R.string.about_body)));
            aboutBodyView.setMovementMethod(new LinkMovementMethod());

            return new AlertDialog.Builder(getActivity())
                    .setView(rootView)
                    .setPositiveButton("Ok",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    dialog.dismiss();
                                }
                            }
                    )
                    .create();
        }
    }
}
