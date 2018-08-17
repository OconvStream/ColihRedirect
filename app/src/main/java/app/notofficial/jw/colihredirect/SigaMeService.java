package app.notofficial.jw.colihredirect;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import app.notofficial.jw.colihredirect.PDO.MMIDial;

public class SigaMeService extends Service {
    public SigaMeService() {
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
}
