package com.pyrotemplardev.campbellferraraapp.Screens;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.pyrotemplardev.campbellferraraapp.R;
import com.pyrotemplardev.campbellferraraapp.Utils.SendMail;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Pyrotemplar on 1/22/2017.
 */

public class EmailFormActivity extends Activity {
    //Declaring EditText
    @Nullable
    @BindView(R.id.nameTextView)
    TextView nameTextView;
    @BindView(R.id.firstNameEditText)
    EditText firstNameEditText;
    @Nullable
    @BindView(R.id.lastNameEditText)
    EditText lastNameEditText;
    @BindView(R.id.emailTextView)
    TextView emailTextView;
    @Nullable
    @BindView(R.id.emailEditText)
    EditText emailEditText;
    @BindView(R.id.phoneTextView)
    TextView phoneTextView;
    @Nullable
    @BindView(R.id.cellPhoneEditText)
    EditText cellPhoneEditText;
    @Nullable
    @BindView(R.id.homePhoneEditText)
    EditText homePhoneEditText;
    @BindView(R.id.addressTextView)
    TextView addressTextView;
    @Nullable
    @BindView(R.id.streetEditText)
    EditText streetEditText;
    @Nullable
    @BindView(R.id.cityEditText)
    EditText cityEditText;
    @Nullable
    @BindView(R.id.statePicker)
    Spinner statePicker;
    @Nullable
    @BindView(R.id.zipCodeEditText)
    EditText zipCodeEditText;
    @BindView(R.id.messageTextView)
    TextView messageTextView;
    @Nullable
    @BindView(R.id.messageEditText)
    EditText messageEditText;
    @BindView(R.id.buttonSend)
    Button buttonSend;


    final private String SUBJECT_CONSULTATION = "Consultation Request";
    final private String SUBJECT_MESSAGE = "Contact Request";

    String ImagePath;
    Boolean isMessageForm;
    private String pictureName;
    private SendMail sm;

    static EmailFormActivity emailFormActivity;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.email_form_layout);
        Intent intent = getIntent();
        isMessageForm = intent.getBooleanExtra("message", true);
        ButterKnife.bind(this);
        emailFormActivity = this;

        //Creating SendMail object
        if (isMessageForm) {
            buttonSend.setText("Send Message");
            sm = new SendMail(this, SUBJECT_MESSAGE);
        }else {
            buttonSend.setText("Send Request");
            sm = new SendMail(this, SUBJECT_CONSULTATION);
        }

        vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
//        //Adding click listener
//        sendButton.setOnClickListener(this);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            //Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    public static EmailFormActivity getInstance(){
        return   emailFormActivity;
    }


    private void sendEmail() {
        //Getting content for email
        String name = firstNameEditText.getText().toString().trim() + " "
                + lastNameEditText.getText().toString().trim();
        String emailAddress = emailEditText.getText().toString().trim();
        String cellPhone = cellPhoneEditText.getText().toString().trim();
        String homePhone = homePhoneEditText.getText().toString().trim();
        String address = streetEditText.getText().toString() + "\n\n"
                + cityEditText.getText().toString().trim() + ", "
                +statePicker.getSelectedItem().toString().trim()+ " "
                + zipCodeEditText.getText().toString().trim();
        String message = messageEditText.getText().toString().trim();

        String wholeMessage = "Name: " + name + "\n\n" +
                "Email: " + emailAddress + "\n\n" +
                "Cell Phone: " + cellPhone + "\n\n" +
                "Home Phone: " + homePhone + "\n\n" +
                "Address:\n\n" + address + "\n\n\n\n" +
                message;

        sm.addMessage(wholeMessage);
        //Executing sendmail to send email
        sm.execute();
    }

    @OnClick(R.id.buttonSend)
    public void buttonSend() {
        vibrator.vibrate(50);
        if (FieldValidation()){
            sendEmail();
        }
    }

    @OnClick(R.id.attachButton)
    public void attachButton() {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        vibrator.vibrate(50);
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
                pictureName = ImagePath.substring(ImagePath.lastIndexOf("/") + 1);

                //create View for image
                CreateAttachmentView(pictureName);
                sm.addAttachment(ImagePath);


            }
        } catch (Exception e) {
            e.printStackTrace();
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

    public void CreateAttachmentView(String pictureName) {
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View attachmentView = vi.inflate(R.layout.attachment_view_layout, null);


        // fill in any details dynamically here
        TextView textView = (TextView) attachmentView.findViewById(R.id.attachmentTextView);
        textView.setText(pictureName);

        ImageButton removeAttachmentButton = (ImageButton) attachmentView.findViewById(R.id.removeAttachmentButton);
        removeAttachmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vibrator.vibrate(50);
                removeView(attachmentView);
            }
        });

        addView(attachmentView);
    }

    private void addView(View view) {
        // insert into main view
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.attachmentLayout);
        viewGroup.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void removeView(View view) {
        ViewGroup viewGroup = (ViewGroup) findViewById(R.id.attachmentLayout);
        viewGroup.removeView(view);
    }

    private Boolean FieldValidation() {
        Boolean isValid = true;
            if (firstNameEditText.getText().toString().isEmpty() || lastNameEditText.getText().toString().isEmpty()) {
                nameTextView.setTextColor(Color.RED);
                isValid = false;
            }
            if (emailEditText.getText().toString().isEmpty()) {
                emailTextView.setTextColor(Color.RED);
                isValid = false;
            }

        if(!isMessageForm){
            if(cellPhoneEditText.getText().toString().isEmpty() && homePhoneEditText.getText().toString().isEmpty()){
                phoneTextView.setTextColor(Color.RED);
                isValid = false;
            }
            if(streetEditText.getText().toString().isEmpty() || cityEditText.getText().toString().isEmpty() || statePicker.getSelectedItem().toString().equals("State")|| zipCodeEditText.getText().toString().isEmpty()){
                addressTextView.setTextColor(Color.RED);
                isValid = false;
            }
        }
        return isValid;

    }

}
