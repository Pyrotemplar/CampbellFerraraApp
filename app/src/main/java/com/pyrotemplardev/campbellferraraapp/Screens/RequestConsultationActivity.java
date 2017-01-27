package com.pyrotemplardev.campbellferraraapp.Screens;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.pyrotemplardev.campbellferraraapp.R;
import com.pyrotemplardev.campbellferraraapp.Utils.MailSender;
import com.pyrotemplardev.campbellferraraapp.Utils.SendMail;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Pyrotemplar on 1/22/2017.
 */

public class RequestConsultationActivity extends Activity {
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


    String SUBJECT = "Consultation Request";

    String ImagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_form_layout);
        ButterKnife.bind(this);

//
//        //Adding click listener
//        sendButton.setOnClickListener(this);
    }


    private void sendEmail() {
        //Getting content for email
        String name = emailEditTextFirstName.getText().toString().trim() + " "
                + emailEditTextLastName.getText().toString().trim();
        String emailAddress = emailEditTextEmail.getText().toString().trim();
        String cellPhone = EmailEditTextCellPhone.getText().toString().trim();
        String homePhone = EmailEditTextHomePhone.getText().toString().trim();
        String address = emailEditTextStreet.getText().toString()+ "\n\n"
                + emailEditTextCity.getText().toString().trim() + ", "
                + emailEditTextState.getText().toString().trim() + " "
                + emailEditTextZipCode.getText().toString().trim();
        String message = emailEditTextMessage.getText().toString().trim();

        String wholeMessage = "Name: " + name + "\n\n" +
                "Email: " + emailAddress + "\n\n" +
                "Cell Phone: " + cellPhone + "\n\n" +
                "Home Phone: " + homePhone + "\n\n" +
                "Address:\n\n" + address + "\n\n" +
                message;


        //Creating SendMail object
        SendMail sm = new SendMail(this, SUBJECT, wholeMessage, ImagePath);

        //Executing sendmail to send email
        sm.execute();
    }

    @OnClick(R.id.buttonSend)
    public void buttonSend() {
        sendEmail();
    }

    @OnClick(R.id.attachButton)
    public void attachButton() {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 1 && resultCode == RESULT_OK
                    && null != data) {
                Uri selectedImageUri = data.getData();
                ImagePath = getPath(getApplicationContext(), selectedImageUri);
                //Log.d("Picture Path", picturePath);

            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }

    public static String getPath(Context context, Uri uri) {
        String result = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            result = cursor.getString(column_index);
        }
        cursor.close();
        if (result == null) {
            result = "Not found";
        }
        return result;
    }

}
