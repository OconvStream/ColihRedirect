package app.notofficial.jw.colihredirect.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;


import android.os.Bundle;

import android.view.View;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import java.util.GregorianCalendar;

import app.notofficial.jw.colihredirect.R;
import app.notofficial.jw.colihredirect.service.SigaMeService;

public class SchedulerActivity extends AppCompatActivity {

    private static final int RQ_FOLLOW_ME_SERVICE = 1101;
    private int mJobId = 0;
    private ComponentName mServiceComponent;
    private static final String MIDNIGHT = "00:00:00";
    private AlarmManager alarmManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);
    }


    public void finishJob(View v) {
        Toast.makeText(SchedulerActivity.this,
                "Cancelado o utimo agendamento",
                Toast.LENGTH_SHORT).show();
    }

    public void cancelAllJobs(View v) {
        Toast.makeText(SchedulerActivity.this,
                "Cancelado TODOS agendamento",
                Toast.LENGTH_SHORT).show();
    }

    public void scheduleJob(View v) {
        Context context  = getApplicationContext();
        if (alarmManager == null) {
             alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        }
        Calendar cur_cal = new GregorianCalendar();
        cur_cal.setTimeInMillis(System.currentTimeMillis());//set the current time and date for this calendar

        Calendar cal = new GregorianCalendar();
        cal.add(Calendar.DAY_OF_YEAR, cur_cal.get(Calendar.DAY_OF_YEAR));
        cal.set(Calendar.HOUR_OF_DAY, 21);
        cal.set(Calendar.MINUTE, 33);
        cal.set(Calendar.SECOND, cur_cal.get(Calendar.SECOND));
        cal.set(Calendar.MILLISECOND, cur_cal.get(Calendar.MILLISECOND));
        cal.set(Calendar.DATE, cur_cal.get(Calendar.DATE));
        cal.set(Calendar.MONTH, cur_cal.get(Calendar.MONTH));

        Intent intent = new Intent(context, SigaMeService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, RQ_FOLLOW_ME_SERVICE, intent, 0);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), 30*1000, pendingIntent);
        Toast.makeText(SchedulerActivity.this,
                "Iniciado agendamento automático",
                Toast.LENGTH_SHORT).show();
    }




}
