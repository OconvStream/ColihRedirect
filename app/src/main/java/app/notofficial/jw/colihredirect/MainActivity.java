package app.notofficial.jw.colihredirect;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import app.notofficial.jw.colihredirect.Impl.StartServiceButtonClickImpl;
import app.notofficial.jw.colihredirect.Util.PermissionCodes;

public class MainActivity extends AppCompatActivity {

    public static boolean isService = false;
    private Button startServiceButton;
    private StartServiceButtonClickImpl startButtonClick;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.startServiceButton = findViewById(R.id.btn_startService);
        this.startButtonClick   = new StartServiceButtonClickImpl(this);

        startServiceButton.setOnClickListener(this.startButtonClick);

        startServiceButton.callOnClick();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PermissionCodes.PERMISSIONS_REQUEST_CALL_PHONE) {
            this.startButtonClick.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
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
