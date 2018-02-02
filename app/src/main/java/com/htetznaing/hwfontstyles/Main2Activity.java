package com.htetznaing.hwfontstyles;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
    }

    public void fb(View view) {
            try {
                Uri uri = Uri.parse("fb-messenger://user/627699334104477");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }catch (Exception e){
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://m.facebook.com/messages/read/?tid=627699334104477"));
                startActivity(intent);
            }
    }

    public void email(View view) {
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "mmapps2018.com@gmail.com", null));
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Hauwei Myanmar Font Styles");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Enter your feedback here");
            startActivity(Intent.createChooser(emailIntent, "Send email..."));
    }
}
