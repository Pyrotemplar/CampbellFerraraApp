package com.pyrotemplardev.campbellferraraapp.Screens;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.pyrotemplardev.campbellferraraapp.R;

/**
 * Created by Pyrotemplar on 1/22/2017.
 */

public class ContatcUsActivity extends Activity implements View.OnClickListener {
    String phoneNumber = "5616019451";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_us_layout);

        ImageButton messageButton = (ImageButton) findViewById(R.id.messageButtonView);
        ImageButton callNowButton = (ImageButton) findViewById(R.id.callNowButtonView);
        ImageButton requestConsultationButton = (ImageButton) findViewById(R.id.requestConsultationButtonView);


        //Adding click listener
        messageButton.setOnClickListener(this);
        callNowButton.setOnClickListener(this);
        requestConsultationButton.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.messageButtonView) {
            Intent intent = new Intent(this, MessageFormActivity.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.callNowButtonView){
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        }
        else if (v.getId() == R.id.requestConsultationButtonView){
            Intent intent = new Intent(this, RequestConsultationActivity.class);
            startActivity(intent);
        }
    }
}
