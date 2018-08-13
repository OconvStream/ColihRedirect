package app.notofficial.jw.colihredirect;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.io.FileDescriptor;

public class SigaMeService extends Service {
    public SigaMeService() {
    }

    private NotificationManager mNM;
    private Bundle b;
    Intent notificationIntent;
    private final IBinder mBinder = new LocalBinder();
    private String newtext;

    public class LocalBinder extends Binder {
        SigaMeService getService() {
            return SigaMeService.this;
        }
    }

    @Override
    public void onCreate () {
        mNM = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        newtext = "BackGroundApp Service Running";

        PendingIntent contentIntent = PendingIntent.getActivity(SigaMeService.this, 0, new Intent(SigaMeService.this,   MainActivity.class), 0);
       // notification.setLatestEventInfo(SigaMeService.this,"BackgroundAppExample", newtext, contentIntent);

        notificationIntent = new Intent(this, MainActivity.class);

        Notification.Builder builder = new Notification.Builder(SigaMeService.this);
        Notification notification = builder.setAutoCancel(false)
                .setTicker("This is a ticker text")
                .setContentTitle("SigaMeAppExample")
                .setContentIntent(contentIntent)
                .setContentText("You Have a new Message")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setOngoing(true)
                .setNumber(100)
                .build();

        notification.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        mNM.notify(R.string.local_service_started, notification);
        showNotification();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }
    public void onDestroy() {
        mNM.cancel(R.string.local_service_started);
        stopSelf();
    }
    private void showNotification() {
        CharSequence text = getText(R.string.local_service_started);


        PendingIntent contentIntent = PendingIntent.getActivity(this, 0,new Intent(this, MainActivity.class), 0);

        Notification.Builder builder = new Notification.Builder(SigaMeService.this);
        Notification notification = builder.setAutoCancel(false)
                .setTicker("This is a ticker text")
                .setContentTitle("SigaMeAppExample")
                .setContentIntent(contentIntent)
                .setContentText("You Have a new Message")
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setOngoing(true)
                .setNumber(100)
                .build();

        notification.flags = Notification.FLAG_ONGOING_EVENT | Notification.FLAG_NO_CLEAR;
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        mNM.notify(R.string.local_service_started, notification);
    }

}
