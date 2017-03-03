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

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Pyrotemplar on 1/22/2017.
 * This activity allows the ueser to learn more about the company.
 */

public class LearnMoreActivity extends Activity implements View.OnClickListener{

    @BindView(R.id.portfolioButton)
    ImageButton portfolioButton;
    @BindView(R.id.reviewsButton)
    ImageButton reviewsButton;
    @BindView(R.id.servicesButton)
    ImageButton servicesButton;
    @BindView(R.id.aboutUsButton)
    ImageButton aboutUsButton;
    @BindView(R.id.fullWebsiteButton)
    ImageButton fullWebsiteButton;

    Vibrator vibrator;

    String webpageURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.learn_more_layout);
        ButterKnife.bind(this);

        portfolioButton.setOnClickListener(this);
        reviewsButton.setOnClickListener(this);
        servicesButton.setOnClickListener(this);
        aboutUsButton.setOnClickListener(this);
        fullWebsiteButton.setOnClickListener(this);

        vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
    }

    //**************************
    //update to use Butterknife

    @Override
    public void onClick(View v) {
        vibrator.vibrate(50);
        if(v.getId() == portfolioButton.getId())
        {
            webpageURL = getResources().getString(R.string.portfolio_link);
        }
        if(v.getId() == reviewsButton.getId())
        {
            webpageURL = getResources().getString(R.string.reviews_link);
        }
        if(v.getId() == servicesButton.getId())
        {
            webpageURL = getResources().getString(R.string.services_link);
        }
        if(v.getId() == aboutUsButton.getId())
        {
            webpageURL = getResources().getString(R.string.about_us_link);
        }
        if(v.getId() == fullWebsiteButton.getId())
        {
            webpageURL = getResources().getString(R.string.full_website_link);
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webpageURL));
        startActivity(browserIntent);

    }
}
