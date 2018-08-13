package app.notofficial.jw.colihredirect;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startService() {
       // Intent it = new Intent(MainActivity.class, SigaMeService.class);
        //startService(it);

    }
}
