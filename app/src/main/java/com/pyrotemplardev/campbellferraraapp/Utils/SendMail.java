package com.pyrotemplardev.campbellferraraapp.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import com.pyrotemplardev.campbellferraraapp.Utils.MailerConfig;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * Created by Pyrotemplar on 1/16/2017.
 */

public class SendMail extends AsyncTask<Void, Void, Void> {


    //Declaring Variables
    private Context context;
    private Session session;

    //Information to send email
    private String email;
    private String subject;
    private String message;

    // attachment path
    String filename = Environment.getExternalStorageDirectory().getPath() + "/rmsINFO.text";

    //Progressdialog to show while sending email
    private ProgressDialog progressDialog;

    //Class Constructor
    public SendMail(Context context, String subject, String message, String ImagePath) {
        //Initializing variables
        this.context = context;
        this.subject = subject;
        this.message = message;
        this.filename = ImagePath;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Showing progress dialog while sending email
        progressDialog = ProgressDialog.show(context, "Sending message", "Please wait...", false, false);
    }


    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        //Dismissing the progress dialog
        progressDialog.dismiss();
        //Showing a success message
        Toast.makeText(context, "Message Sent", Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... params) {


        //Creating properties
        Properties props = new Properties();

        //Configuring properties for Sendgrid
        props.put("mail.smtp.host", "smtp.sendgrid.net");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        //Creating a new session
        session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    //Authenticating the password
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(MailerConfig.USERNAME, MailerConfig.PASSWORD);
                    }
                });

        try {
            //Creating MimeMessage object
            MimeMessage emailMessage = new MimeMessage(session);

            //Setting sender address
            emailMessage.setFrom(new InternetAddress(MailerConfig.SENDEREMAIL));
            //Adding receiver
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(MailerConfig.RECIVEREMAIL));
            //Adding subject
            emailMessage.setSubject(subject);
            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();
            // Now set the actual message
            messageBodyPart.setText(message);
            // Create a multipar message
            Multipart multipart = new MimeMultipart();
            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            //Adding message
            //  emailMessage.setText("Email Address: "+email+"\n"+message);

            // Part two is attachment
          //  messageBodyPart = new MimeBodyPart();
         //   DataSource source = new FileDataSource(filename);
          //  messageBodyPart.setDataHandler(new DataHandler(source));
         //   messageBodyPart.setFileName(filename);
         //   multipart.addBodyPart(messageBodyPart);

            // put together the complete message parts
            emailMessage.setContent(multipart);

            // Send message
            Transport.send(emailMessage);


        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
