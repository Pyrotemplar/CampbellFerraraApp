package com.pyrotemplardev.campbellferraraapp.Screens;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.pyrotemplardev.campbellferraraapp.R;


public class MainActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        ImageButton contactUsButton = (ImageButton) findViewById(R.id.contactUsButtonView);
        ImageButton directionButton = (ImageButton) findViewById(R.id.directionsButtonView);
        ImageButton learnMoreButton = (ImageButton) findViewById(R.id.learnMoreButtonView);


        //Adding click listener
        contactUsButton.setOnClickListener(this);
        directionButton.setOnClickListener(this);
        learnMoreButton.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.contactUsButtonView) {
            Intent intent = new Intent(this, ContatcUsActivity.class);
            startActivity(intent);
        }
        else if(v.getId() == R.id.directionsButtonView){
            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=38.730899,-77.103673"));
            startActivity(intent);
            // if want to give user options for map app.
            //startActivity(Intent.createChooser(intent, "Pick Map"));
        }
        else if (v.getId() == R.id.learnMoreButtonView){
            Intent intent = new Intent(this, LearnMoreActivity.class);
            startActivity(intent);
        }
    }
}
