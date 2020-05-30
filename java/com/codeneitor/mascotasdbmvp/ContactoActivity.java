package com.codeneitor.mascotasdbmvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ContactoActivity extends AppCompatActivity implements View.OnClickListener {

    Session sesion = null;
    ProgressDialog dialogo = null;
    Context context = null;
    EditText etNombre, etEmail, etMensaje;
    TextView tvPara;
    String emailPara, subject, nombre, emailDe, mensaje;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        //Agregamos nuestro ToolBar
        Toolbar toolbar = (Toolbar) findViewById(R.id.miActionBar);
        setSupportActionBar(toolbar);
        //Agregamos soporte al botón de navegación hacia atrás
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Agregamos la huella en nuestro Toolbar
        getSupportActionBar().setIcon(R.drawable.huella_perro);
        // Remove default title text
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        context = this;

        Button login = (Button) findViewById(R.id.btEnviar);
        tvPara = (TextView) findViewById(R.id.tvPara);
        etNombre = (EditText) findViewById(R.id.etNombre);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etMensaje = (EditText) findViewById(R.id.etMensaje);

        login.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        emailPara = tvPara.getText().toString();
        Log.i("DESTINATARIO",""+emailPara);
        nombre = etNombre.getText().toString();
        Log.i("REMITENTE NOMBRE",""+nombre);
        emailDe = etEmail.getText().toString();
        Log.i("REMITENTE EMAIL",""+emailDe);
        subject = "Revision COURSERA";
        mensaje = "Mensaje de: "+ nombre+
                "<BR>En: "+subject+
                "<BR>Mensaje: "+etMensaje.getText().toString()+
                "<BR>Email: "+emailDe;
        Log.i("MENSAJE",""+mensaje);

        Properties props= new Properties();
        props.put("mail.smtp.host","smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port","465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth","true");
        props.put("mail.smtp.port","465");

        sesion = Session.getDefaultInstance(props, new Authenticator(){
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(
                        getResources().getString(R.string.email_auth),
                        getResources().getString(R.string.pass_auth)
                );
            }
        });

        dialogo = ProgressDialog.show(context, "", "Enviando correo...", true);

        RetreiveFeedTask task = new RetreiveFeedTask();
        task.execute();
    }

    class RetreiveFeedTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            try{
                Message mesage = new MimeMessage(sesion);
                //Email de quien realiza la autenticacion
                mesage.setFrom(new InternetAddress(getResources().getString(R.string.email_auth)));
                // Este es el Email del destinatario
                mesage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailPara));
                mesage.setSubject(subject);
                mesage.setContent(mensaje, "text/html; charset=utf8");

                Transport.send(mesage);
            }catch(MessagingException e){
                e.printStackTrace();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String resultado) {
            dialogo.dismiss();
            etNombre.setText("");
            etEmail.setText("");
            etMensaje.setText("");
            Toast.makeText(getApplicationContext(), "Gracias por tu mensaje.", Toast.LENGTH_LONG).show();
        }
    }
}
