package app.notofficial.jw.colihredirect.service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import app.notofficial.jw.colihredirect.config.MMIDial;

public class SigaMeService extends IntentService {


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SigaMeService(String name) {
        super("SigaMeService");
    }

    public class LocalBinder extends Binder {
        SigaMeService getService() {
            return SigaMeService.this;
        }
    }

    @Override
    public void onCreate () {
        MMIDial mmiDial = new MMIDial("081", "997334642");
        mmiDial.prepareDial(MMIDial.DEACTIVATE);
        mmiDial.dial(this);

        mmiDial.prepareDial(MMIDial.ACTIVATE);
        mmiDial.dial(this);
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
        stopSelf();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

    }
}
