package app.notofficial.jw.colihredirect;

import android.app.Service;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.annotation.RequiresApi;

import app.notofficial.jw.colihredirect.Util.AndroidUtil;

@RequiresApi(api = Build.VERSION_CODES.M)
public class SigaMeJobService extends JobService {
    public SigaMeJobService() {
    }


    @Override
    public boolean onStartJob(JobParameters params) {
        Intent service = new Intent(getApplicationContext(), SigaMeService.class);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
            getApplicationContext().startForegroundService(service);
        else
            getApplicationContext().startService(service);

        AndroidUtil.scheduleJob(getApplicationContext()); // reschedule the job
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }


}
