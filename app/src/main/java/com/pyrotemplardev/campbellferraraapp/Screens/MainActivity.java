package com.pyrotemplardev.campbellferraraapp.Screens;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.pyrotemplardev.campbellferraraapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends Activity implements View.OnClickListener {

    @BindView(R.id.contactUsButtonView) ImageButton contactUsButton;
    @BindView(R.id.directionsButtonView) ImageButton directionButton;
    @BindView(R.id.learnMoreButtonView) ImageButton learnMoreButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove title bar
        //  requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


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
