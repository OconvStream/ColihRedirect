package app.notofficial.jw.colihredirect;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class SigaMeService extends Service {
    public SigaMeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
