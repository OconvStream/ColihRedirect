package app.notofficial.jw.colihredirect.PDO;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import app.notofficial.jw.colihredirect.R;
import app.notofficial.jw.colihredirect.Util.AndroidUtil;

public class MMIDial {

    private static final String ACTIVATE_DEACTIVATE_SIGA_ME_REDIRECT_ALL_CODE        = "21";
    private static final String ACTIVATE_DEACTIVATE_SIGA_ME_REDIRECT_UNANSWERED_CODE = "61";
    private static final String ACTIVATE_DEACTIVATE_SIGA_ME_REDIRECT_BUSY_CODE       = "67";
    private static final String ACTIVATE_DEACTIVATE_SIGA_ME_REDIRECT_UNREACHED_CODE  = "62";

    private static final String ACTIVATE_COMMAND_PREFIX          = "*";
    private static final String ACTIVATE_COMMAND_SUFFIX          = Uri.encode("#");
    private static final String DEACTIVATE_COMMAND_PREFIX_SUFFIX = Uri.encode("#");

    private static final String CALL_COMMAND_PREFIX = "tel:";

    public static final int ACTIVATE   = 1;
    public static final int DEACTIVATE = 2;

    private String areaCode = null;
    private String number   = null;
    private Intent executeMMI;

    public MMIDial(String areaCode, String number) {
        this.executeMMI = new Intent(Intent.ACTION_CALL);
        this.areaCode   = areaCode;
        this.number     = number;
    }

    public void prepareDial(int option) {
        if( option == ACTIVATE ) {
            if(number != null) {
                String activateCode = ACTIVATE_COMMAND_PREFIX + ACTIVATE_DEACTIVATE_SIGA_ME_REDIRECT_ALL_CODE + ACTIVATE_COMMAND_PREFIX;
                String activateURI = CALL_COMMAND_PREFIX + activateCode + areaCode + number + ACTIVATE_COMMAND_SUFFIX;
                this.executeMMI.setData(Uri.parse(activateURI));
            }
        }
        else if ( option == DEACTIVATE ) {
            String deactivateCode = DEACTIVATE_COMMAND_PREFIX_SUFFIX + ACTIVATE_DEACTIVATE_SIGA_ME_REDIRECT_ALL_CODE + DEACTIVATE_COMMAND_PREFIX_SUFFIX;
            String deactivateURI = CALL_COMMAND_PREFIX + deactivateCode;
            this.executeMMI.setData(Uri.parse(deactivateURI));
        }
    }

    public void dial(Context service) {
        try {
            service.startActivity(this.executeMMI);
        }catch (SecurityException ex) {
            AndroidUtil.showToast ( service, service.getString( R.string.grant_permission ), Toast.LENGTH_SHORT );
        }
    }

    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
