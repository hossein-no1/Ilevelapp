package com.example.pneumatic;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AboutUs extends AppCompatActivity {

    Context context = this;
    Toolbar toolbarMain;
    ImageView imgInstagram, imgTelegram;
    TextView txtPhone1, txtPhone2, txtVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        //find view all items
        findViewByIds();

        allClicke();

    }

    private void allClicke() {

        toolbarMain.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();

            }
        });


        imgInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //enter your social network link
                intentToLink("");
            }
        });


        imgTelegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //enter your social network link
                intentToLink("");
            }
        });


        txtPhone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //enter your phone number
                intentToNumberPhone(txtPhone1.getText().toString());
            }
        });


        txtPhone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //enter your phone number
                intentToNumberPhone(txtPhone2.getText().toString());
            }
        });


    }

    private void findViewByIds() {

        //find view all items
        toolbarMain = (Toolbar) findViewById(R.id.toolBarMainAAS);
        imgInstagram = findViewById(R.id.imginstagramAAS);
        imgTelegram = findViewById(R.id.imgTelegramAAS);
        txtPhone1 = findViewById(R.id.txtNumberPhone1AAS);
        txtPhone2 = findViewById(R.id.txtNumberPhone2AAS);
        txtVersion = findViewById(R.id.txtVersionAAS);

    }

    private void intentToLink(String url) {

        Uri uri = Uri.parse(url);
        Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);

        try {
            startActivity(likeIng);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse(url)));
        }

    }

    private void intentToNumberPhone(String url) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+url.trim().toString()));
        startActivity(intent);
    }

}