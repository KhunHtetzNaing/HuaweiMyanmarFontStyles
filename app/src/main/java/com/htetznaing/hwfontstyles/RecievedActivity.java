package com.htetznaing.hwfontstyles;

import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.snatik.storage.Storage;

import java.io.File;
import java.util.ArrayList;

public class RecievedActivity extends AppCompatActivity {
    Generator generator;
    String workPath;
    Storage storage;
    String font,name;
    AdRequest adRequest;
    AdView banner;
    InterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recieved);
        font = getIntent().getStringExtra("font");
        name = getIntent().getStringExtra("name");
        setTitle(name);
        getSupportActionBar().setElevation(0);

        workPath = Environment.getExternalStorageDirectory()+"/Android/data/"+getPackageName()+"/.cache/";
        storage = new Storage(this);
        storage.createDirectory(workPath);

        adRequest = new AdRequest.Builder().build();
        banner = findViewById(R.id.adView);
        banner.loadAd(adRequest);

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId("ca-app-pub-2780984156359274/7162381758");
        interstitialAd.loadAd(adRequest);
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                loadAD();
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                loadAD();
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                loadAD();
            }
        });
    }

    public void loadAD(){
        if (!interstitialAd.isLoaded()){
            interstitialAd.show();
        }
    }

    public void showAD(){
        if (interstitialAd.isLoaded()){
            interstitialAd.show();
        }else{
            interstitialAd.loadAd(adRequest);
        }
    }

    public void install(){
        showAD();
        String version = "1.0";
        String osversion = Build.VERSION.RELEASE;
        String screen = "HD";

        if (new File(workPath+font).exists()==true){
            generator = new Generator(RecievedActivity.this,workPath+font,name,"Khun Htetz Naing",screen,version,osversion);
            String text = generator.build();
            Toast.makeText(RecievedActivity.this, text, Toast.LENGTH_LONG).show();
        }else{
            generator = new Generator(RecievedActivity.this,workPath+font,name,"Khun Htetz Naing",screen,version,osversion);
            generator.assets2SD(RecievedActivity.this,"leelar.jpg",workPath,"htetz.zip");
            generator.unZipWithPass(workPath+"htetz.zip","<@LeeLar4/>",workPath);
            storage.deleteFile(workPath+"htetz.zip");
            if (new File(workPath+font).exists()==true) {
                generator = new Generator(RecievedActivity.this, workPath + font, name, "Khun Htetz Naing", screen, version, osversion);
                String text = generator.build();
                Toast.makeText(RecievedActivity.this, text, Toast.LENGTH_LONG).show();
            }
        }
    }

    public void reset(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("WARNING!");
        builder.setMessage("This function will deleted\nall your font styles!");
        builder.setPositiveButton("Process anyway", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showAD();
                File file [] = new File(Environment.getExternalStorageDirectory()+"/HWThemes/").listFiles();
                try {
                    for (int o=0;o<file.length;o++){
                        if (file[o].isDirectory()){
                        }else{
                            for (int p=0;p<getNames().size();p++){
                                if (file[o].getName().equals(getNames().get(p)+".hwt")){
                                    storage.deleteFile(file[o].toString());
                                }
                            }
                        }
                    }

                    File file1[] = new File(Environment.getExternalStorageDirectory()+"/Android/data/"+getPackageName()).listFiles();
                    for (int l=0;l<file1.length;l++){
                        if (file1[l].isDirectory()){
                            storage.deleteDirectory(file1[l].toString());
                        }else{
                            storage.deleteFile(file1[l].toString());
                        }
                    }
                    Toast.makeText(RecievedActivity.this, "Completed!\nDeleted all fonts :)", Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(RecievedActivity.this, "Error!\nSomething was wrong :(", Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showAD();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void install(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Attention!");
        builder.setMessage("You want to install "+name+" Font ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                install();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showAD();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void change(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Attention!");
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View view1 = layoutInflater.inflate(R.layout.change,null);
        WebView webView = view1.findViewById(R.id.webView);
        webView.loadUrl("file:///android_asset/change.html");
        builder.setView(view1);
        builder.setPositiveButton("Change Font", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showAD();
                try{
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.setComponent(new ComponentName("com.huawei.android.thememanager","com.huawei.android.thememanager/.HwThemeManagerActivity"));
                    startActivity(intent);
                }catch (Exception e){
                    Intent intent = getPackageManager().getLaunchIntentForPackage("com.huawei.android.thememanager");
                    startActivity(intent);
                }
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        showAD();
        switch (item.getItemId()){
            case R.id.about:
                startActivity(new Intent(this,Main2Activity.class));break;
            case R.id.help:
                startActivity(new Intent(this,Help.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public ArrayList<String> getNames(){
        ArrayList<String> goo = new ArrayList<>();
        String s1 = "Dancing Zawgyi\n" +
                "Darcy\n" +
                "Eain Mat\n" +
                "Ghost\n" +
                "Heart\n" +
                "Htinshu\n" +
                "iPhone Zawgyi\n" +
                "Jojar\n" +
                "Kason\n" +
                "KhinHninsi\n" +
                "Love\n" +
                "Matrix\n" +
                "Nattaw\n" +
                "Nayon\n" +
                "NgaYe\n" +
                "NotoSans Zawgyi\n" +
                "NotoSans Zawgyi Mix\n" +
                "Ooredoo Myanmar\n" +
                "Padauk Zawgyi\n" +
                "PaOh Zawgyi\n" +
                "Pyatho\n" +
                "Sagar\n"+
                "Smart Zawgyi\n" +
                "Tabaung\n" +
                "Tabodwe\n" +
                "Tabodwe Mix\n" +
                "Tagu\n" +
                "Tawthalin\n" +
                "Transformet\n" +
                "Tansaungmone\n" +
                "Tawthalin\n" +
                "Ubuntu Zawgyi\n" +
                "Warso\n" +
                "Wargaung\n" +
                "YoeYar Zawgyi\n" +
                "Yuzana\n" +
                "Zawgyi 2\n" +
                "Zawgyi One\n" +
                "Zawgyi Yang";
        String s2 []= s1.split("\n");
        for (int i=0;i<s2.length;i++){
            goo.add(s2[i]);
        }

        return goo;
    }
}
