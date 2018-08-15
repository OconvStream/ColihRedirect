package app.notofficial.jw.colihredirect;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class SigaMeService extends Service {
    public SigaMeService() {
    }

    private TelephonyManager mTelephonyManager;
    public class LocalBinder extends Binder {
        SigaMeService getService() {
            return SigaMeService.this;
        }
    }

    private boolean isTelephonyEnabled() {
        if (mTelephonyManager != null) {
            if (mTelephonyManager.getSimState() ==
                    TelephonyManager.SIM_STATE_READY) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onCreate () {

        mTelephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

        if (isTelephonyEnabled()) {
            //Log.d(TAG, getString(R.string.telephony_enabled));
            // Todo: Register the PhoneStateListener.

            // Todo: Check for permission here.

        } else {
            Toast.makeText(this,
                    "Telephony not enabled",
                    Toast.LENGTH_LONG).show();
        }

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
    private void showNotification() {
        Context ctx = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(ctx, "Notificação", duration);
        toast.show();
    }

}
