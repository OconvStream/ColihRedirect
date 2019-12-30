package app.notofficial.jw.colihredirect;

import android.content.BroadcastReceiver;

import android.content.Context;
import android.content.Intent;
import android.os.Build;

import app.notofficial.jw.colihredirect.util.AndroidUtil;


public class AutoStart extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            AndroidUtil.scheduleJob(context);
        }else {

        }
    }
}
