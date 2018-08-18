package app.notofficial.jw.colihredirect.adapter;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Toast;

import app.notofficial.jw.colihredirect.R;
import app.notofficial.jw.colihredirect.service.SigaMeService;
import app.notofficial.jw.colihredirect.util.AndroidUtil;
import app.notofficial.jw.colihredirect.util.PermissionCodes;

public class StartServiceButtonClickImpl implements View.OnClickListener, ActivityCompat.OnRequestPermissionsResultCallback {

    private Context context;

    public StartServiceButtonClickImpl(Context context) {
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        if(checkPermission()) {
            //isService = true;
            startService();

        }
        else {
            AndroidUtil.showToast(this.context, context.getString(R.string.grant_permission), Toast.LENGTH_LONG);
            requestPermission();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void startServiceApi(Intent intent) {
        this.context.startForegroundService(new Intent(this.context,SigaMeService.class));
    }

    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this.context, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions((Activity) this.context, new String[]{Manifest.permission.CALL_PHONE}, PermissionCodes.PERMISSIONS_REQUEST_CALL_PHONE);
    }

    @Override
    public void onRequestPermissionsResult(int i, @NonNull String[] strings, @NonNull int[] ints) {
        startService();
    }
    private void startService() {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O)
            this.context.startService(new Intent(context, SigaMeService.class));
        else
            this.startServiceApi(new Intent(context, SigaMeService.class));

        ((Activity)context).finish();
    }
}
