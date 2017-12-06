package com.example.razor.implicit_intent;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteEditText;
    private EditText mshareEditText;
    private EditText mcallEditText;
    private EditText msmsEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = (EditText) findViewById(R.id.edtbrowser);
        mshareEditText = (EditText) findViewById(R.id.edttextshare);
        mcallEditText = (EditText) findViewById(R.id.edtphone);
        msmsEditText = (EditText) findViewById(R.id.edtsms);
    }

    public void openwebsite(View view) {
        // Get the URL text.
        String url = "http://" + (mWebsiteEditText.getText().toString());

        // Parse the URI and create the intent.
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        // Find an activity to hand the intent and start that activity.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    public void texhshare(View view) {
        String txt = mshareEditText.getText().toString();
        ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();
    }
    //private static final int CALL = 1;

    public void phonecall(View view) {

        String nomortelp = String.format("tel: %s", mcallEditText.getText().toString()) ;
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        dialIntent.setData(Uri.parse(nomortelp));
        if (dialIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(dialIntent);
        } else {
            Log.d("phonecall:", "cannot call this number");
        }


   /*     if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this, new String[] { Manifest.permission.CALL_PHONE},CALL);
        }else {
            Intent intentphone = new Intent(Intent.ACTION_CALL);

            intentphone.setData(Uri.parse("tel:085320288825"));
            startActivity(intentphone);
        }
*/

    }

    public void sendsms(View view) {
        String smsNumber = String.format("smsto: %s", mcallEditText.getText().toString());
        String sms = msmsEditText.getText().toString();
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        smsIntent.setData(Uri.parse(smsNumber));
        smsIntent.putExtra("sms_body", sms);
        if (smsIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(smsIntent);
        } else {
            Log.d("SMS:", "Can't resolve app for ACTION_SENDTO Intent.");
        }
    }


}
