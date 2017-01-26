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

/**
 * Created by Pyrotemplar on 1/22/2017.
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
    }

    //**************************
    //update to use Butterknife

    @Override
    public void onClick(View v) {

        if(v.getId() == portfolioButton.getId())
        {
            webpageURL = "http://campbellferrara.com/design-portfolio/";
        }
        if(v.getId() == reviewsButton.getId())
        {
            webpageURL = "http://campbellferrara.com/reviews/";
        }
        if(v.getId() == servicesButton.getId())
        {
            webpageURL = "http://campbellferrara.com/landscaping-services/";
        }
        if(v.getId() == aboutUsButton.getId())
        {
            webpageURL = "http://campbellferrara.com/about/";
        }
        if(v.getId() == fullWebsiteButton.getId())
        {
            webpageURL = "http://campbellferrara.com/";
        }
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webpageURL));
        startActivity(browserIntent);

    }
}
