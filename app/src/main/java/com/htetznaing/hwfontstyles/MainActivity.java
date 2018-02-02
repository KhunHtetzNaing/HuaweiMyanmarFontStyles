package com.htetznaing.hwfontstyles;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.snatik.storage.Storage;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    ArrayList<String> images = new ArrayList<>();
    MgAdapter adapter;
    AdRequest adRequest;
    InterstitialAd interstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        images = getImages();
        listView = findViewById(R.id.listView);
        adapter = new MgAdapter(this,images);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String font = getFontName().get(i);
                String name = getNames().get(i);
                Intent intent = new Intent(MainActivity.this,RecievedActivity.class);
                intent.putExtra("font",font);
                intent.putExtra("name",name);
                showAD();
                startActivity(intent);
            }
        });
        adRequest = new AdRequest.Builder().build();
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

    public ArrayList<String> getImages(){
        ArrayList<String> arrayList = new ArrayList<>();
        String s1 = "dancing.png\n" +
                "darcy.png\n" +
                "eainmat.png\n" +
                "ghost.png\n" +
                "heart.png\n" +
                "htinshu.png\n" +
                "izawgyi.png\n" +
                "jojar.png\n" +
                "kason.png\n" +
                "khninsi.png\n" +
                "love.png\n" +
                "matrix.png\n" +
                "nattaw.png\n" +
                "nayon.png\n" +
                "ngaye.png\n" +
                "notosans.png\n" +
                "notosansmix.png\n" +
                "ooredoo.png\n" +
                "padauk.png\n" +
                "paoh.png\n" +
                "pyatho.png\n" +
                "sagar.png\n" +
                "szg.png\n" +
                "tabaung.png\n" +
                "tabodwe.png\n" +
                "tabodwemix.png\n" +
                "tagu.png\n" +
                "tdg.png\n" +
                "tran.png\n" +
                "tsm.png\n" +
                "ttl.png\n" +
                "ubuntu.png\n" +
                "warso.png\n" +
                "wg.png\n" +
                "yoeyar.png\n" +
                "yuzana.png\n" +
                "zg2.png\n" +
                "zo.png\n" +
                "zy.png";
        String s2[] = s1.split("\n");
        for (int i=0;i<s2.length;i++){
            arrayList.add(s2[i]);
        }

        return arrayList;
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

    public ArrayList<String> getFontName(){
        ArrayList<String> goo = new ArrayList<>();
        String s1 = "dancing.ttf\n" +
                "dc.ttf\n" +
                "eainmat.ttf\n" +
                "ghost.ttf\n" +
                "heart.ttf\n" +
                "htinshu.ttf\n" +
                "izawgyi.ttf\n" +
                "jojar.ttf\n" +
                "kason.ttf\n" +
                "khnisi.ttf\n" +
                "love.ttf\n" +
                "matrix.ttf\n" +
                "nattaw.ttf\n" +
                "nayon.ttf\n" +
                "ngaye.ttf\n" +
                "notosans.ttf\n" +
                "notosansmix.ttf\n" +
                "ooredoo.ttf\n" +
                "padauk.ttf\n" +
                "paoh.ttf\n" +
                "pyatho.ttf\n" +
                "sagar.ttf\n" +
                "szg.ttf\n" +
                "tabaung.ttf\n" +
                "tabodwemix.ttf\n" +
                "tagu.ttf\n" +
                "tbd.ttf\n" +
                "tdg.ttf\n" +
                "tran.ttf\n" +
                "tsm.ttf\n" +
                "ttl.ttf\n" +
                "ubuntu.ttf\n" +
                "warso.ttf\n" +
                "wg.ttf\n" +
                "yoeyar.ttf\n" +
                "yuzana.ttf\n" +
                "z2.ttf\n" +
                "zo.ttf\n" +
                "zy.ttf";
        String s2 []= s1.split("\n");
        for (int i=0;i<s2.length;i++){
            goo.add(s2[i]);
        }

        return goo;
    }

    public void share(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,"Beautiful Myanmar Font Style For Huawei Download Free Here : play.google.com/store/apps/details?id="+getPackageName());
        startActivity(Intent.createChooser(intent,"Huawei Myanmar Font Styles"));
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

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Attention!");
        builder.setMessage("Do you want to exit ?");
        builder.setIcon(R.drawable.icon);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                showAD();
                finish();
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
}
