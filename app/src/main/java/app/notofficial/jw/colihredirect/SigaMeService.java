package app.notofficial.jw.colihredirect;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import app.notofficial.jw.colihredirect.PDO.MMIDial;
import app.notofficial.jw.colihredirect.firebase.Schedule;
import io.paperdb.Paper;

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
        Paper.init(getApplicationContext());
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.w("STOP", "self");
        throw new UnsupportedOperationException("Not yet implemented");

    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        dial();
        return START_NOT_STICKY;
    }

    public void onDestroy() {
        Log.w("STOP", "self");
        stopSelf();

    }

    private void dial() {

        final Date currentDate = Calendar.getInstance().getTime();

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

         FirebaseDatabase.getInstance().getReference().child("schedule").child(dateFormat.format(currentDate)).addListenerForSingleValueEvent(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                 Schedule schedule = null;
                 Schedule scheduleFound = null;


                 Date scheduledDate = null;
                 for(DataSnapshot child : dataSnapshot.getChildren()) {
                     schedule = child.getValue(Schedule.class);

                     SimpleDateFormat hourFormat = new SimpleDateFormat("yyyyMMddHHmmss");

                     try {
                         scheduledDate = hourFormat.parse(schedule.getDate() + schedule.getTime().replace(":", ""));
                     } catch (ParseException e) {
                         e.printStackTrace();
                         return;
                     }
                     long curr = currentDate.getTime();
                     long sche = scheduledDate.getTime();
                     long result = scheduledDate.getTime() - currentDate.getTime();
                     long inMinutes = TimeUnit.MILLISECONDS.toMinutes(result);

                     if(inMinutes >= 0 && inMinutes < 26) {
                         scheduleFound = schedule;
                     }
                 }

                 Schedule lastSchedule = Paper.book().read("schedule");

                 if(scheduleFound != null) {
                     Log.i("SigaMeService", "Schedule found!");
                     if(lastSchedule != null && lastSchedule.getId() == scheduleFound.getId()) {
                         Log.i("SigaMeService", "Same schedule. Not re-scheduling");
                         return;
                     }

                     Log.i("SigaMeService", "Scheduling number " + scheduleFound.getNumber());

                     MMIDial mmiDial = new MMIDial(scheduleFound.getArea(), scheduleFound.getNumber());
                     mmiDial.prepareDial(MMIDial.ACTIVATE);
                     mmiDial.dial(SigaMeService.this);

                     Log.i("SigaMeService", "Number " + scheduleFound.getNumber() + " scheduled");

                     Log.i("SigaMeService", "Saving current scheduled number ");

                     Paper.book().write("schedule", scheduleFound);

//                     mmiDial.prepareDial(MMIDial.DEACTIVATE);
//                     mmiDial.dial(this);
                 }
                 else {
                     Log.w("SigaMeService", "No schedule found!");
                 }

             }

             @Override
             public void onCancelled(@NonNull DatabaseError databaseError) {
             }
         });
    }
}
