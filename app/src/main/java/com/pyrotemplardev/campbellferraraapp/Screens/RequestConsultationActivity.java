package com.pyrotemplardev.campbellferraraapp.Screens;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.pyrotemplardev.campbellferraraapp.R;
import com.pyrotemplardev.campbellferraraapp.Utils.SendMail;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Pyrotemplar on 1/22/2017.
 */

public class RequestConsultationActivity extends Activity implements View.OnClickListener {
    //Declaring EditText
    @Nullable
    @BindView(R.id.emailEditTextFirstName)
    EditText emailEditTextFirstName;
    @Nullable
    @BindView(R.id.emailEditTextLastName)
    EditText emailEditTextLastName;
    @Nullable
    @BindView(R.id.emailEditTextEmail)
    EditText emailEditTextEmail;
    @Nullable
    @BindView(R.id.EmailEditTextCellPhone)
    EditText EmailEditTextCellPhone;
    @Nullable
    @BindView(R.id.EmailEditTextHomePhone)
    EditText EmailEditTextHomePhone;
    @Nullable
    @BindView(R.id.emailEditTextStreet)
    EditText emailEditTextStreet;
    @Nullable
    @BindView(R.id.emailEditTextCity)
    EditText emailEditTextCity;
    @Nullable
    @BindView(R.id.emailEditTextState)
    EditText emailEditTextState;
    @Nullable
    @BindView(R.id.emailEditTextZipCode)
    EditText emailEditTextZipCode;
    @Nullable
    @BindView(R.id.emailEditTextMessage)
    EditText emailEditTextMessage;
    //Send button
    @BindView(R.id.buttonSend)
    Button sendButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_form_layout);
        ButterKnife.bind(this);


        //Adding click listener
        sendButton.setOnClickListener(this);
    }


    private void sendEmail() {
        //Getting content for email
        String name = emailEditTextFirstName.getText().toString().trim() + " "
                    + emailEditTextLastName.getText().toString().trim();
        String emailAddress = emailEditTextEmail.getText().toString().trim();
        String cellPhone = EmailEditTextCellPhone.getText().toString().trim();
        String homePhone = EmailEditTextHomePhone.getText().toString().trim();
        String Address = emailEditTextStreet.getText().toString().trim() + "\n"
                    + emailEditTextCity.getText().toString().trim() + ", "
                    + emailEditTextState.getText().toString().trim() + " "
                    + emailEditTextZipCode.getText().toString().trim();
        ;
        String Message = emailEditTextMessage.getText().toString().trim();



        //Creating SendMail object
        //SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
       // sm.execute();
    }

    @Override
    public void onClick(View v) {
        sendEmail();
    }
}
