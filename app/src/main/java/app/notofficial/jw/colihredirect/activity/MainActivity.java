package app.notofficial.jw.colihredirect.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import app.notofficial.jw.colihredirect.service.SigaMeService;
import app.notofficial.jw.colihredirect.util.AndroidUtil;


public class MainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        if(checkPermission()) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                AndroidUtil.scheduleJob(this);
            else {
                Toast.makeText(this, "Esse aplicativo não está funcionando em versões anteriores à Marshmellow (23)", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        else {
            requestPermission();
        }
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //PERMISSIONS_REQUEST_CALL_PHONE = 16
        if (requestCode == 16) {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                AndroidUtil.scheduleJob(getApplicationContext());
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        stopService( new Intent(MainActivity.this, SigaMeService.class) );
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        //PERMISSIONS_REQUEST_CALL_PHONE = 16
        ActivityCompat.requestPermissions((Activity) this, new String[]{Manifest.permission.CALL_PHONE}, 16);
    }
}
