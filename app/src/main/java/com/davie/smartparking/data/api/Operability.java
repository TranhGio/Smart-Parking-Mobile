package com.davie.smartparking.data.api;

//import static androidx.core.content.ContextCompat.startActivity;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.telephony.SmsManager;

import com.davie.smartparking.ui.onboarding.MainActivity;

public class Operability{

    public void SendSMS(String phone, String text){

        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone,null, text,null,null);

    }

    public Intent NavigateLocation(String Source, String Destination){
        Uri uri = Uri.parse("https://www.google.com/maps/dir"+Source+"/"+Destination);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.setPackage("com.google.android.apps.maps");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        return intent;
    }
}
