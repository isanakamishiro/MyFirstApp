package net.isanakamishiro.myfirstapp.util;

import android.app.AlertDialog;
import android.content.Context;

/**
 * Created by isana on 2017/09/09.
 */

public class Alert {

    private Alert() {
    }

    public static void showDialog(Context context, int messageid, int titleid) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(messageid)
                .setTitle(titleid)
                .setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public static  void showDialog(Context context, String message, int titleid) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setTitle(titleid)
                .setPositiveButton(android.R.string.ok, null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
