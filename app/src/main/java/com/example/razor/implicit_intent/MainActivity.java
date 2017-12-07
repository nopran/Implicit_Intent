package com.example.razor.implicit_intent;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
  //Inisialisasi Variabel
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

    //Fungsi yang digunakan pada website
    public void openwebsite(View view) {
        // Mendapatkan URL dari Edit Text
        String url = "http://" + (mWebsiteEditText.getText().toString());

        // Parse nilai uri ke url.
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

       //menampilkan activity sesuai dengan kondisi
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    //Fungsi yang digunakan untuk share
    public void texhshare(View view) {
        //Mendapatkan text dari edittext
        String txt = mshareEditText.getText().toString();

        //fungsi intent builder digunakan untuk memilih target intent pada aplikasi lain
        ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();
    }

    //Fungsi yang digunakan untuk call/dial
    public void phonecall(View view) {
        //memformat hasil inputan user
        String nomortelp = String.format("tel: %s", mcallEditText.getText().toString()) ;
        //mendefinisikan intent ke action_dial
        Intent dialIntent = new Intent(Intent.ACTION_DIAL);
        //parse uri data dari inputan user
        dialIntent.setData(Uri.parse(nomortelp));
        //pengecekan status
        if (dialIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(dialIntent);
        } else {
            Log.d("phonecall:", "cannot call this number");
        }

    }

    //fungsi yang digunakan untuk mengirim sms
    public void sendsms(View view) {
        //memformat nomor sms
        String smsNumber = String.format("smsto: %s", mcallEditText.getText().toString());
        //menampung string inputan ke string sms
        String sms = msmsEditText.getText().toString();
        //mendefinisikan intent ke ACTION_SENDTO
        Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
        //parse data
        smsIntent.setData(Uri.parse(smsNumber));
        //memasukkan nilai dari string sms ke SMS BODY
        smsIntent.putExtra("sms_body", sms);
        //Cek Status
        if (smsIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(smsIntent);
        } else {
            Log.d("SMS:", "Can't resolve app for ACTION_SENDTO Intent.");
        }
    }

}
