package com.pyrotemplardev.campbellferraraapp.Screens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;

import com.pyrotemplardev.campbellferraraapp.R;

/**
 * Created by Pyrotemplar on 1/22/2017.
 * This activity is used to allow the user to make contact
 */

public class ContatcUsActivity extends Activity implements View.OnClickListener {

    Vibrator vibrator;

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


        vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
    }



    @Override
    public void onClick(View v) {
        vibrator.vibrate(50);
        if (v.getId() == R.id.messageButtonView) {
            Intent intent = new Intent(this, EmailFormActivity.class);
            intent.putExtra("message", true);
            startActivity(intent);
        }
        else if(v.getId() == R.id.callNowButtonView){
            Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + getResources().getString(R.string.PHONE_NUMBER)));
            startActivity(intent);
        }
        else if (v.getId() == R.id.requestConsultationButtonView){
            Intent intent = new Intent(this, EmailFormActivity.class);
            intent.putExtra("message", false);
            startActivity(intent);
        }
    }
}
