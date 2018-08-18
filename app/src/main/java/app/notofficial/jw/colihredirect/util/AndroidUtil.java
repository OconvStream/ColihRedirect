package app.notofficial.jw.colihredirect.util;

import android.content.Context;
import android.widget.Toast;

public class AndroidUtil {

    public static void showToast(Context context, CharSequence message, int duration) {
        Toast toast = Toast.makeText(context, message, duration);
        toast.show();
    }
}
