package app.notofficial.jw.colihredirect.activity;

import android.content.Intent;
import android.os.Build;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import app.notofficial.jw.colihredirect.R;
import app.notofficial.jw.colihredirect.adapter.StartServiceButtonClickImpl;
import app.notofficial.jw.colihredirect.service.SigaMeService;
import app.notofficial.jw.colihredirect.util.AndroidUtil;


public class MainActivity extends AppCompatActivity {

    private StartServiceButtonClickImpl startButtonClick;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                AndroidUtil.scheduleJob(getApplicationContext());

        Button startServiceButton = findViewById(R.id.btn_startService);
        this.startButtonClick   = new StartServiceButtonClickImpl(this);

        startServiceButton.setOnClickListener(this.startButtonClick);

        startServiceButton.callOnClick();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //PERMISSIONS_REQUEST_CALL_PHONE = 16
        if (requestCode == 16) {
            this.startButtonClick.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        stopService( new Intent(MainActivity.this, SigaMeService.class) );
    }
}
