package com.example.razor.implicit_intent;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mWebsiteEditText;
    private EditText mlocationEditText;
    private EditText mshareEditText;
    private EditText mcallEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebsiteEditText = (EditText) findViewById(R.id.edtbrowser);
        mlocationEditText = (EditText) findViewById(R.id.edtloc);
        mshareEditText = (EditText) findViewById(R.id.edttextshare);
        mcallEditText = (EditText) findViewById(R.id.edtphone);
    }

    public void openwebsite(View view){
        // Get the URL text.
        String url = mWebsiteEditText.getText().toString();

        // Parse the URI and create the intent.
        Uri webpage =  Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        // Find an activity to hand the intent and start that activity.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }

    }

    public void openlocation(View view){
        // Get the URL text.
        String loc = mlocationEditText.getText().toString();

        // Parse the URI and create the intent.


        Uri addressUri = Uri.parse("geo:0,0?q="+loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        // Find an activity to hand the intent and start that activity.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }

    }

    public void texhshare(View view){
        String txt = mshareEditText.getText().toString();
        ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();
    }

    public void phonecall(View view){

        String nomortelp = String.format("tel: %s", mcallEditText.getText().toString());
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        // Set the data for the intent as the phone number.
        dialIntent.setData(Uri.parse(nomortelp));
        if (dialIntent.resolveActivity(getPackageManager()) !=null){
            startActivity(dialIntent);
        }else {
            Log.d("phonecall:", "cannot call this number");
        }

    }

}
