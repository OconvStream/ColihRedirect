package app.notofficial.jw.colihredirect.Util;

import android.content.Context;
import android.widget.Toast;

import app.notofficial.jw.colihredirect.R;

public class AndroidUtil {

    public static void showToast(Context context, CharSequence message, int duration) {
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}
