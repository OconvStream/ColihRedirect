package app.notofficial.jw.colihredirect.PDO;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import app.notofficial.jw.colihredirect.R;

public class MMIDial {

    private static final String ACTIVATE_DEACTIVATE_SIGA_ME_REDIRECT_ALL_CODE        = "21";
    private static final String ACTIVATE_DEACTIVATE_SIGA_ME_REDIRECT_UNANSWERED_CODE = "61";
    private static final String ACTIVATE_DEACTIVATE_SIGA_ME_REDIRECT_BUSY_CODE       = "67";
    private static final String ACTIVATE_DEACTIVATE_SIGA_ME_REDIRECT_UNREACHED_CODE  = "62";

    private static final String ACTIVATE_COMMAND_PREFIX          = "*";
    private static final String ACTIVATE_COMMAND_SUFFIX          = Uri.encode("#");
    private static final String DEACTIVATE_COMMAND_PREFIX_SUFFIX = Uri.encode("#");

    private static final String CALL_COMMAND_PREFIX = "tel:";

    private static final int ACTIVATE   = 1;
    private static final int DEACTIVATE = 2;

    private String areaCode = null;
    private String number   = null;
    private Intent executeMMI;

    MMIDial(String areaCode, String number) {
        this.executeMMI = new Intent(Intent.ACTION_CALL);
        this.areaCode   = areaCode;
        this.number     = number;
    }

    private void prepareDial(int option) {
        if( option == ACTIVATE ) {
            if(number != null) {
                String activateCode = ACTIVATE_COMMAND_PREFIX + ACTIVATE_DEACTIVATE_SIGA_ME_REDIRECT_ALL_CODE + ACTIVATE_COMMAND_PREFIX;
                String activateURI = CALL_COMMAND_PREFIX + activateCode + areaCode + number + ACTIVATE_COMMAND_SUFFIX;
                this.executeMMI.setData(Uri.parse(activateURI));
            }
        }
        else if ( option == DEACTIVATE ) {
            String deactivateCode = DEACTIVATE_COMMAND_PREFIX_SUFFIX + ACTIVATE_DEACTIVATE_SIGA_ME_REDIRECT_ALL_CODE + DEACTIVATE_COMMAND_PREFIX_SUFFIX;
            String deactivateURI = deactivateCode;
            this.executeMMI.setData(Uri.parse(deactivateURI));
        }

    }

    public void Dial(Service service) {
        try {
            service.startActivity(this.executeMMI);
        }catch (SecurityException ex) {
            Context ctx = service.getApplicationContext();

            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(ctx, R.string.grant_permission, duration);
            toast.show();
        }
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
