package app.notofficial.jw.colihredirect;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static boolean isService = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startserviceButton = (Button) findViewById(R.id.btn_startService);
        startserviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O)
                    startService(new Intent(MainActivity.this,SigaMeService.class));
                else {
                    startServiceApi(new Intent(MainActivity.this,SigaMeService.class));
                }


                Intent startMain = new Intent(Intent.ACTION_MAIN);
                startMain.addCategory(Intent.CATEGORY_HOME);
                startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(startMain);
                isService = true;
            }
        });

        Button stopserviceButton = (Button) findViewById(R.id.btn_StopService);
        stopserviceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this,SigaMeService.class));
                Intent stopMain = new Intent(Intent.ACTION_MAIN);
                stopMain.addCategory(Intent.CATEGORY_HOME);
                stopMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(stopMain);

                isService = false;
            }
        });
    }

    @TargetApi(Build.VERSION_CODES.O)
    protected void startServiceApi(Intent intent) {
         startForegroundService(new Intent(MainActivity.this,SigaMeService.class));
    }
    @Override
    protected void onResume() {
        super.onResume();
        stopService(new Intent(MainActivity.this,
                SigaMeService.class));
        if(isService)
        {
            TextView tv = (TextView) findViewById(R.id.txtVw_Message);
            tv.setText("Service Resumed");
            isService = false;
        }
    }
}
