package com.pyrotemplardev.campbellferraraapp.Screens;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pyrotemplardev.campbellferraraapp.R;
import com.pyrotemplardev.campbellferraraapp.Utils.SendMail;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by Pyrotemplar on 1/16/2017.
 */

public class MessageFormActivity extends Activity implements View.OnClickListener {

    //Declaring EditText
    @BindView(R.id.messageEditTextEmail) TextView messageEditTextEmail;
    @BindView(R.id.messageEditTextSubject) TextView messageEditTextSubject;
    @BindView(R.id.messageEditTextMessage) TextView messageEditTextMessage;

    // send button
    @BindView(R.id.buttonSend) Button sendButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_form_layout);
        ButterKnife.bind(this);


        //Adding click listener
        sendButton.setOnClickListener(this);
    }


    private void sendEmail() {
        //Getting content for email
        String email = messageEditTextEmail.getText().toString().trim();
        String subject = messageEditTextSubject.getText().toString().trim();
        String message = messageEditTextMessage.getText().toString().trim();


        //Creating SendMail object
        SendMail sm = new SendMail(this, subject);

        //Executing sendmail to send email
        sm.execute();
    }

    @Override
    public void onClick(View v) {
        sendEmail();
    }
}
